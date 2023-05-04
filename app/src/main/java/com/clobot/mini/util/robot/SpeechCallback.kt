package com.clobot.mini.util.robot

import android.os.RemoteException
import com.ainirobot.coreservice.client.speech.SkillCallback
import timber.log.Timber

class SpeechCallback : SkillCallback() {
    @Throws(RemoteException::class)
    override fun onSpeechParResult(s: String) {
        Timber.i("onSpeechParResult: $s")
    }

    @Throws(RemoteException::class)
    override fun onStart() {
        Timber.i("onStart")
    }

    @Throws(RemoteException::class)
    override fun onStop() {
        Timber.i("onStop")
    }

    @Throws(RemoteException::class)
    override fun onVolumeChange(i: Int) {
        Timber.i("onVolumeChange: $i");
    }

    @Throws(RemoteException::class)
    override fun onQueryEnded(i: Int) {
        Timber.i("onQueryEnded: $i")
    }

    @Throws(RemoteException::class)
    override fun onQueryAsrResult(asrResult: String) {
        Timber.i("onQueryAsrResult: $asrResult")
    }
}