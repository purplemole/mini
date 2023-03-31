package com.example.googlecloudmanager.domain

import android.content.Context
import android.media.MediaPlayer
import android.os.Environment
import android.util.Log
import com.example.googlecloudmanager.common.Constant.TTS_PATH
import com.example.googlecloudmanager.common.Language
import com.example.googlecloudmanager.common.Resource
import com.example.googlecloudmanager.common.util.AudioEmitter
import com.example.googlecloudmanager.data.GoogleCloudApi
import com.example.googlecloudmanager.data.GoogleCloudApi.Companion.translateClient
import com.google.api.gax.rpc.ClientStream
import com.google.api.gax.rpc.ResponseObserver
import com.google.api.gax.rpc.StreamController
import com.google.cloud.speech.v1.*
import com.google.cloud.translate.v3.LocationName
import com.google.cloud.translate.v3.TranslateTextRequest
import com.google.cloud.translate.v3.TranslateTextResponse
import jxl.Sheet
import jxl.Workbook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ClosedSendChannelException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean

class GoogleCloudRepository constructor(
    private val context: Context,
    private val api: GoogleCloudApi,
    private val type: String
) {
    var language: Language
        get() = api.getLanguage()
        set(value) = api.setLanguage(value)

    private val TAG = "GoogleCloudRepository"
    private val mp = MediaPlayer()
    private var audioEmitter: AudioEmitter = AudioEmitter()
    private var isRecoding: Boolean = false
    private lateinit var requestStream: ClientStream<StreamingRecognizeRequest>
    private var speech_text: String = ""
    private var vocaData: List<String> = listOf()

    init {
        vocaData = readVocabularyData("vocabulary_22_09_30.xls", 1) //22.09.30 - hiqi추가
    }

    /** TTS speak function
     *
     * */
    @OptIn(ExperimentalCoroutinesApi::class)
    fun speak(_context: Context?, text: String, isLocal: Boolean = false): Flow<Resource<Boolean>> =
        callbackFlow {
            try {
                val file =
                    if (isLocal) {
                        val langPath = when (language) {
                            Language.Korean -> "/KO"
                            Language.English -> "/EN"
                            Language.Japanese -> "/JP"
                            Language.Chinese -> "/CH"
                        }
                        val temp = File("$TTS_PATH$langPath", "$text.mp3")
                        if (temp.exists()) temp
                        else File("$TTS_PATH/KO", "$text.mp3")
                    } else {
                        val path = _context?.externalCacheDir
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
                            Log.i(TAG, "speak onComplete")
                            if (!channel.isClosedForSend) {
                                sendBlocking(Resource.Complete(false))
                                close()
                            }
                        } catch (e: ClosedSendChannelException) {
                            Log.e(TAG, "CloseedSendChannel Exception", e)
                        } catch (e: Throwable) {
                            Log.e(TAG, "error? ${e.message}", e)
                        }
                    }
                    mp.setDataSource(it.fd)
                    mp.prepare()
                    mp.start()
                    sendBlocking(Resource.Listen(true))
                    it.close()
                }
            } catch (e: Exception) {
                e.message?.let { Log.e(TAG, it) }
            }

            awaitClose {
                Log.i(TAG, "speak::callback flow finish.")
            }
        }.flowOn(Dispatchers.IO)

    fun pause() {
        mp.pause()
    }

    fun start() {
        mp.start()
    }

    fun stop() {
        if (mp.isPlaying) {
            mp.stop()
        }
    }

    // Google Speech
    fun speechStop() {
        Log.i(TAG, "speech_stop called")
        if (!isRecoding) return
        try {
            if (requestStream.isSendReady) {
                requestStream.closeSend()
            }
            audioEmitter.stop()
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        } finally {
            isRecoding = false
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun speechToText(withDelay: Boolean = true): Flow<Resource<String>> =
        callbackFlow<Resource<String>> {
            try {
                val isFirstRequest = AtomicBoolean(true)
                isRecoding = true
                speech_text = ""

                Log.d(TAG, "stream speechToText")

                val callback = object : ResponseObserver<StreamingRecognizeResponse> {
                    override fun onStart(controller: StreamController?) {
                        Log.i(TAG, "start recognize")
                    }

                    override fun onResponse(response: StreamingRecognizeResponse?) {
                        when {
                            response?.resultsCount!! > 0 -> {
                                val transcript =
                                    response.getResults(0).getAlternatives(0).transcript
                                speech_text = transcript
                                sendBlocking(Resource.Listen(transcript))
                            }
                            else -> Log.e(TAG, response.error.toString())
                        }
                    }

                    override fun onError(t: Throwable?) {
                        Log.e(TAG, "an error occurred", t)
                    }

                    override fun onComplete() {
                        Log.d(TAG, "stream closed")
                        sendBlocking(Resource.Complete(speech_text))
                    }
                }

                Log.d(TAG, "stream create")
                requestStream =
                    api.getSpeechClient().streamingRecognizeCallable().splitCall(callback)

                if (withDelay) {
                    sendBlocking(Resource.Loading("START"))
                    delay(3000)
                }
                sendBlocking(Resource.Loading("END"))

                //beep
                val file =
                    File(Environment.getExternalStorageDirectory().absolutePath + "/$type" + "/sounds" + "/vr_input_effect_sound_QI.mp3")
                FileInputStream(file).use {
                    val beepPlayer = MediaPlayer()
                    beepPlayer.setOnCompletionListener {
                        beepPlayer.release()
                    }
                    beepPlayer.setDataSource(it.fd)
                    beepPlayer.prepare()
                    beepPlayer.start()
                }

                audioEmitter.start { bytes ->
                    val builder = StreamingRecognizeRequest.newBuilder()
                        .setAudioContent(bytes)
                    if (isFirstRequest.getAndSet(false)) {
                        val speechContext: SpeechContext =
                            SpeechContext.newBuilder().addAllPhrases(vocaData).build()

                        builder.streamingConfig = StreamingRecognitionConfig.newBuilder()
                            .setConfig(
                                RecognitionConfig.newBuilder()
                                    .setLanguageCode(api.getLanguage().toString())
                                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                                    .addSpeechContexts(speechContext)
                                    .setSampleRateHertz(16000).build()
                            )
                            .setInterimResults(true)
                            .setSingleUtterance(true)
                            .build()
                    }
                    requestStream.send(builder.build())
                }

                // 8초 뒤 입력 종료 (3초 뒤 시작 , 5초 동안 음성 인식)
                delay(5000)
                speechStop()
            } catch (e: IOException) {
                Log.e(TAG, e.message, e)
                sendBlocking(Resource.Error("speechToText::Couldn't reach server. Check your internet connection."))
            } catch (e: Exception) {
                Log.e(TAG, e.message, e)
                sendBlocking(Resource.Error("speechToText::Something long."))
            }

            awaitClose {
                Log.i(TAG, "speechToText::callback flow finish.")
                //speechStop()
            }
        }.flowOn(Dispatchers.IO)

    fun translate(text: String): String {
        Log.i(TAG, "translate text: $text")
        try {
            val request: TranslateTextRequest = TranslateTextRequest.newBuilder()
                .setParent(
                    LocationName.newBuilder().setProject(GoogleCloudApi.projectId)
                        .setLocation("global").build()
                        .toString()
                )
                .setMimeType("text/plain")
                .setSourceLanguageCode(language.toString())
                .setTargetLanguageCode("ko")
                .addContents(text)
                .build()
            val response: TranslateTextResponse = translateClient.translateText(request)
            val translatedText = response.getTranslations(0).translatedText
            Log.i(TAG, "translate: $translatedText")
            return translatedText
        } catch (e: Exception) {
            Log.e(TAG, "translate Error: $e")
            throw RuntimeException(e)
        }
    }

    fun readVocabularyData(path: String, col: Int): MutableList<String> {
        val stream = context.resources?.assets?.open(path)
        val workBook = Workbook.getWorkbook(stream)
        val localSheet: Sheet = workBook.getSheet(0)

        val arrReadData: MutableList<String> = mutableListOf()
        val totalRow = localSheet.rows
        val totalCol = localSheet.columns

        if (col > totalCol) {
            Log.e("ExcelMgr", "wrong column idx($col)")
            return arrReadData
        }

        for (idx in 1 until totalRow) {
            val rowContents = localSheet.getCell(col, idx)
            arrReadData.add(rowContents.contents)
        }
        Log.i("ExcelMgr", "vocabulary updateed")
        return arrReadData
    }
}