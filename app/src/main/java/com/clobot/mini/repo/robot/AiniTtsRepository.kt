package com.clobot.mini.repo.robot

import android.util.Log
import com.ainirobot.coreservice.client.listener.TextListener
import com.ainirobot.coreservice.client.speech.entity.TTSEntity
import com.clobot.mini.data.robot.TtsMode
import com.clobot.mini.MainApplication
import javax.inject.Inject


class AiniTtsRepository @Inject constructor() {
    private val mSkillApi = MainApplication.getInstance()?.getSkillApi()

    fun tts(
        mode: TtsMode,
        text: String = "",
    ) {
        when (mode) {
            TtsMode.Play -> mSkillApi?.playText(TTSEntity("sid-1234567890", text), mTextListener)
            TtsMode.Stop -> mSkillApi?.stopTTS()
            TtsMode.Query -> mSkillApi?.queryByText(text)
        }
    }

    private val mTextListener: TextListener = object : TextListener() {
        override fun onStart() {
            super.onStart()
            Log.i(TAG, "onStart")
        }

        override fun onStop() {
            super.onStop()
            Log.i(TAG, "onStop")
        }

        override fun onComplete() {
            super.onComplete()
            Log.i(TAG, "onComplete")
        }

        override fun onError() {
            super.onError()
            Log.i(TAG, "onError")
        }
    }

    companion object {
        private const val TAG = "TtsRepo"
    }
}