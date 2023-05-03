package com.example.googlecloudmanager.domain

import android.content.Context
import android.media.MediaPlayer
import com.example.googlecloudmanager.common.Constant.TTS_PATH
import com.example.googlecloudmanager.common.Resource
import com.example.googlecloudmanager.data.GoogleCloudApi
import com.google.cloud.speech.v1.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import javax.inject.Inject

class GoogleCloudRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: GoogleCloudApi,
): IGoogleCloudRepository {
    private val mp = MediaPlayer()

    /**
     * TTS speak function
     * */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun speak(text: String, isLocal: Boolean): Flow<Resource<Boolean>> =
        callbackFlow {
            try {
                val file =
                    if (isLocal) {
                        val temp = File(TTS_PATH, "$text.mp3")
                        if (temp.exists()) temp
                        else throw FileNotFoundException()
                    } else {
                        val path = context.externalCacheDir
                        File(path, "output.mp3")
                    }
                if (!isLocal) api.textToAudio(text, file)
                sendBlocking(Resource.Loading(true))

                FileInputStream(file).use { it ->
                    mp.reset()
                    mp.setOnErrorListener { mediaPlayer, i, i2 ->
                        true
                    }
                    mp.setOnCompletionListener { _ ->
                        try {
                            Timber.i("speak onComplete")
                            if (!channel.isClosedForSend) {
                                sendBlocking(Resource.Complete(false))
                                close()
                            }
                        } catch (e: ClosedSendChannelException) {
                            Timber.e("CloseedSendChannel Exception", e)
                        } catch (e: Throwable) {
                            Timber.e("error? ${e.message}", e)
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
            }
        }
            .flowOn(Dispatchers.IO)

    override fun stop() {
        if (mp.isPlaying) {
            mp.stop()
        }
    }
}