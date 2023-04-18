package com.clobot.mini.util.robot

import android.os.RemoteException
import android.util.Log
import com.ainirobot.coreservice.client.speech.SkillCallback

class SpeechCallback : SkillCallback() {
    @Throws(RemoteException::class)
    override fun onSpeechParResult(s: String) {
        Log.i(TAG, "onSpeechParResult: $s")
    }

    @Throws(RemoteException::class)
    override fun onStart() {
        Log.i(TAG, "onStart")
    }

    @Throws(RemoteException::class)
    override fun onStop() {
        Log.i(TAG, "onStop")
    }

    @Throws(RemoteException::class)
    override fun onVolumeChange(i: Int) {
        Log.i(TAG, "onVolumeChange: $i");
    }

    @Throws(RemoteException::class)
    override fun onQueryEnded(i: Int) {
        Log.i(TAG, "onQueryEnded: $i")
    }

    @Throws(RemoteException::class)
    override fun onQueryAsrResult(asrResult: String) {
        Log.i(TAG, "onQueryAsrResult: $asrResult")
    }

    companion object {
        private val TAG = SpeechCallback::class.java.name
    }
}