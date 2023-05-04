package com.example.googlecloudmanager.data

import android.content.Context
import com.example.googlecloudmanager.R
import com.example.googlecloudmanager.common.Constant
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.texttospeech.v1.AudioConfig
import com.google.cloud.texttospeech.v1.AudioEncoding
import com.google.cloud.texttospeech.v1.SynthesisInput
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse
import com.google.cloud.texttospeech.v1.TextToSpeechClient
import com.google.cloud.texttospeech.v1.TextToSpeechSettings
import com.google.cloud.texttospeech.v1.VoiceSelectionParams
import com.google.protobuf.ByteString
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class GoogleCloudApiImpl @Inject constructor(
    @ApplicationContext val context: Context,
) : GoogleCloudApi {
    private lateinit var projectId: String

    // use on repository
    private lateinit var ttsClient: TextToSpeechClient

    init {
        init()
    }

    private fun init() {
        Timber.d("intialize GoogleCloudApi")
        val stream = context.resources.openRawResource(R.raw.credential)
        val credentials: GoogleCredentials = GoogleCredentials.fromStream(stream)
            .createScoped(Constant.GOOGLE_SCOPE)
        projectId = (credentials as ServiceAccountCredentials).projectId

        // Initialize Text-To-Speech V1
        val textToSppechSettings = TextToSpeechSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
            .build()
        ttsClient = TextToSpeechClient.create(textToSppechSettings)

        stream.close()
    }


    override fun textToAudio(text: String, file: File) {
        Timber.d("textToAudio")
        val input: SynthesisInput = SynthesisInput.newBuilder().setText(text).build()
        val voice: VoiceSelectionParams = VoiceSelectionParams.newBuilder()
            .setLanguageCode("ko-KR")
            .setName("ko-KR-Wavenet-C")
            .build()
        val audioConfig: AudioConfig =
            AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3)
                .setSampleRateHertz(44100).build()

        val response: SynthesizeSpeechResponse? =
            ttsClient.synthesizeSpeech(input, voice, audioConfig)

        val audioContents: ByteString = response!!.audioContent
        FileOutputStream(file).use { out ->
            out.write(audioContents.toByteArray())
            Timber.i("Audio content written to file \"output.mp3\"")
            out.close()
        }
    }

}