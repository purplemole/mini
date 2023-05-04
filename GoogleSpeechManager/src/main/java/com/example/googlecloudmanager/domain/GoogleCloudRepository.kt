package com.example.googlecloudmanager.domain

import android.content.Context
import android.media.MediaPlayer
import com.example.googlecloudmanager.common.Constant.TTS_PATH
import com.example.googlecloudmanager.data.Resource
import com.example.googlecloudmanager.data.GoogleCloudApi
import com.google.cloud.speech.v1.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

class GoogleCloudRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: GoogleCloudApi,
) : IGoogleCloudRepository {
    private val mp = MediaPlayer()

    /**
     * TTS speak function using GoogleAPI credential
     * */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun speak(text: String): Flow<Resource<Boolean>> =
        callbackFlow {
            try {
                sendBlocking(Resource.Loading(true))

                val path = context.externalCacheDir
                val file = File(path, "output.mp3")
                api.textToAudio(text, file)

                FileInputStream(file).use {
                    mp.reset()
                    mp.setOnErrorListener { mediaPlayer, i, i2 ->
                        true
                    }
                    mp.setOnCompletionListener { _ ->
                        Timber.i("speak onComplete")
                        if (!channel.isClosedForSend) {
                            sendBlocking(Resource.Complete(false))
                            close()
                        }
                    }
                    mp.setDataSource(it.fd)
                    mp.prepare()
                    mp.start()
                    sendBlocking(Resource.Listen(true))
                    it.close()
                }
            } catch (e: Exception) {
                e.message?.let {
                    Timber.e(it)
                }
                sendBlocking(Resource.Error(e.message ?: "TTS Error", false))
            }

            awaitClose {
                Timber.i("speak::callback flow finish.")
                mp.reset()
            }
        }
            .flowOn(Dispatchers.IO)


    /**
     * TTS speak function using Local mp3 file
     * */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun localSpeak(filename: String): Flow<Resource<Boolean>> = callbackFlow {
        try {
            sendBlocking(Resource.Loading(true))

            val file = File(TTS_PATH, "$filename.mp3")

            if (!file.exists()) {
                Timber.d("TTS-Local fileNotFoundException")
                sendBlocking(Resource.Error("TTS-Local fileNotFoundException", false))
                channel.close()
            }

            FileInputStream(file).use { it ->
                mp.reset()
                mp.setOnErrorListener { mediaPlayer, i, i2 ->
                    true
                }
                mp.setOnCompletionListener { _ ->
                    Timber.i("speak onComplete")
                    if (!channel.isClosedForSend) {
                        sendBlocking(Resource.Complete(false))
                        close()
                    }
                }
                mp.setDataSource(it.fd)
                mp.prepare()
                mp.start()
                sendBlocking(Resource.Listen(true))
                it.close()
            }
        } catch (e: Exception) {
            e.message?.let { Timber.e(it) }
        }

        awaitClose {
            Timber.i("speak::callback flow finish.")
            mp.reset()
        }
    }

    override fun stop() {
        if (mp.isPlaying) {
            mp.stop()
        }
    }
}