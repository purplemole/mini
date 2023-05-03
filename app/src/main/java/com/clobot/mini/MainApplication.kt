package com.clobot.mini

import android.app.Application
import android.content.Context
import android.os.HandlerThread
import android.util.Log
import com.ainirobot.coreservice.client.ApiListener
import com.ainirobot.coreservice.client.RobotApi
import com.ainirobot.coreservice.client.module.ModuleCallbackApi
import com.ainirobot.coreservice.client.speech.SkillApi
import com.clobot.mini.util.robot.ModuleCallback
import com.clobot.mini.util.robot.SpeechCallback
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
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

        // 참고: https://github.com/JakeWharton/timber/blob/trunk/timber-sample/src/main/java/com/example/timber/ExampleApp.java
        // TODO: Release 일때 Custom DebugTree 정의
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
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
                addApiCallBack()
                initSkillApi()
                Log.i(TAG, "handleApiConnected")
            }

            // Disconnect RobotOS
            override fun handleApiDisconnected() {
                Log.i(TAG, "handleApiDisconnected")
            }
        })
    }

    private fun addApiCallBack() {
        RobotApi.getInstance().setCallback(mModuleCallback)
        RobotApi.getInstance().setResponseThread(mApiCallbackThread)
        Log.d(TAG, "CoreService connected ")
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
        private var mApplication: MainApplication? = null
        private var mContext: Context? = null

        fun getInstance(): MainApplication? {
            return mApplication
        }

        private const val TAG = "RobotOS"
    }
}