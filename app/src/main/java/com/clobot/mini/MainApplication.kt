package com.clobot.mini

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.HandlerThread
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
                Timber.i("handleApiDisabled")
            }

            /**
             * Server connected, set callback to handle message
             * Start connect RobotOS, init and make it ready to use
             */
            override fun handleApiConnected() {
                addApiCallBack()
                initSkillApi()
                Timber.i("handleApiConnected")
            }

            // Disconnect RobotOS
            override fun handleApiDisconnected() {
                Timber.i("handleApiDisconnected")
            }
        })
    }

    private fun addApiCallBack() {
        RobotApi.getInstance().setCallback(mModuleCallback)
        RobotApi.getInstance().setResponseThread(mApiCallbackThread)
        Timber.d("CoreService connected ")
    }

    private fun initSkillApi() {
        mSkillApi = SkillApi()
        val apiListener: ApiListener = object : ApiListener {
            override fun handleApiDisabled() {}

            // Handle speech service
            override fun handleApiConnected() {
                mSkillApi!!.registerCallBack(mSkillCallback)
                mInit = true
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
        @SuppressLint("StaticFieldLeak")
        private var mApplication: MainApplication? = null
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null
        private var mInit: Boolean = false

        fun getInstance(): MainApplication? {
            return mApplication
        }

        fun isRobotInit(): Boolean {
            return mInit
        }
    }
}