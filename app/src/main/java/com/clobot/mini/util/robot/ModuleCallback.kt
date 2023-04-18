package com.clobot.mini.util.robot

import android.os.RemoteException
import android.util.Log
import com.ainirobot.coreservice.client.module.ModuleCallbackApi

class ModuleCallback : ModuleCallbackApi() {
    /**
     * Receive speech request
     * @param reqId
     * @param reqType Speech type
     * @param reqText Speech text
     * @param reqParam Speech param
     * @throws RemoteException
     */
    @Throws(RemoteException::class)
    override fun onSendRequest(
        reqId: Int,
        reqType: String,
        reqText: String,
        reqParam: String,
    ): Boolean {
        Log.d(
            TAG,
            "New request:  type is:$reqType text is:$reqText reqParam = $reqParam"
        )
        val text =
            "New request:  type is:$reqType text is:$reqText reqParam = $reqParam"
        return true
    }

    /**
     * hardware error callback
     * @param function
     * @param type
     * @param message
     * @throws RemoteException
     */
    @Throws(RemoteException::class)
    override fun onHWReport(function: Int, type: String, message: String) {
        Log.i(
            TAG,
            "onHWReport function:$function type:$type message:$message"
        )
    }

    /**
     * Suspend system, after this message, RobotOS can not work with this APP
     * @throws RemoteException
     */
    @Throws(RemoteException::class)
    override fun onSuspend() {
        Log.d(TAG, "onSuspend")
    }

    /**
     * Recovery system, after this message, RobotOS can work with this APP again.
     * @throws RemoteException
     */
    @Throws(RemoteException::class)
    override fun onRecovery() {
        Log.d(TAG, "onRecovery")
    }

    companion object {
        private val TAG = ModuleCallback::class.java.name
    }
}