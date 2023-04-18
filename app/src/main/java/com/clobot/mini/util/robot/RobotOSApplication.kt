package com.clobot.mini.util.robot

import android.app.Application
import android.content.Context
import android.os.HandlerThread
import android.util.Log
import com.ainirobot.coreservice.client.ApiListener
import com.ainirobot.coreservice.client.RobotApi
import com.ainirobot.coreservice.client.module.ModuleCallbackApi
import com.ainirobot.coreservice.client.speech.SkillApi

class RobotOSApplication: Application() {
    private var mSkillApi: SkillApi? = null

    private var mSkillCallback: SpeechCallback? = null
    private var mModuleCallback: ModuleCallbackApi? = null
    private var mApiCallbackThread: HandlerThread? = null

    override fun onCreate() {
        super.onCreate()
        mContext = this
        mApplication = this
        init()
        initRobotApi()
    }

    private fun init() {
        mSkillCallback = SpeechCallback()
        mModuleCallback = ModuleCallback()
        mApiCallbackThread = HandlerThread("RobotOSDemo")
        mApiCallbackThread!!.start()
    }

    private fun initRobotApi() {
        RobotApi.getInstance().connectServer(mContext, object : ApiListener {
            override fun handleApiDisabled() {
                Log.i(TAG, "handleApiDisabled")
            }

            /**
             * Server connected, set callback to handle message
             * Start connect RobotOS, init and make it ready to use
             */
            override fun handleApiConnected() {
                Log.i(TAG, "handleApiConnected")
                addApiCallBack()
                initSkillApi()
            }

            // Disconnect RobotOS
            override fun handleApiDisconnected() {
                Log.i(TAG, "handleApiDisconnected")
            }
        })
    }

    private fun addApiCallBack() {
        Log.d(TAG, "CoreService connected ")
        RobotApi.getInstance().setCallback(mModuleCallback)
        RobotApi.getInstance().setResponseThread(mApiCallbackThread)
    }

    private fun initSkillApi() {
        mSkillApi = SkillApi()
        val apiListener: ApiListener = object : ApiListener {
            override fun handleApiDisabled() {}

            // Handle speech service
            override fun handleApiConnected() {
                mSkillApi!!.registerCallBack(mSkillCallback)
            }

            // Disconnect speech service
            override fun handleApiDisconnected() {}
        }
        mSkillApi!!.addApiEventListener(apiListener)
        mSkillApi!!.connectApi(mContext)
    }

    fun getSkillApi(): SkillApi? {
        return if (mSkillApi!!.isApiConnectedService) {
            mSkillApi
        } else null
    }

    companion object {
        private var mApplication: RobotOSApplication? = null
        private var mContext: Context? = null

        fun getInstance(): RobotOSApplication? {
            return mApplication
        }

        private const val TAG = "RobotOS"
    }
}