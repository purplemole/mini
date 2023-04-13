package com.clobot.mini.repo.robot

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ainirobot.coreservice.client.Definition
import com.ainirobot.coreservice.client.RobotApi
import com.ainirobot.coreservice.client.listener.ActionListener
import com.ainirobot.coreservice.client.listener.CommandListener
import com.ainirobot.coreservice.client.robotsetting.RobotSettingApi
import com.clobot.mini.repo.RobotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AiniRobotRepository @Inject constructor() : RobotRepository {
    private val robotApi = RobotApi.getInstance()
//    val chargeStatus = robotApi.chargeStatus
    private val _dockingState = MutableStateFlow(false)
    override val dockingState: StateFlow<Boolean> = _dockingState

    private var checkTimes: Int = 0

    override fun initialize(): Boolean {
        checkTimes++
        return if (checkTimes > 10) {
            // Temporary code
            _dockingState.value = true
            false
        } else if (robotApi.isApiConnectedService
            && robotApi.isActive
        ) {
            true
        } else {
            Handler(Looper.getMainLooper()).postDelayed(Runnable { initialize() }, 300)
        }
    }

    override fun checkDockingStation() {
        _dockingState.value = robotApi.isChargePileExits
    }

    override fun getVersion(): String {
        return robotApi.version
    }

    fun getBattery(): String {
        return "Battery level:" +
                RobotSettingApi.getInstance()
                    .getRobotString(Definition.ROBOT_SETTINGS_BATTERY_INFO) + "%"
    }

    // 기본 동작
    fun basicMove(
        // reqId = 0, speed = 0.0f, distance = Float.MAX_VALUE, avoid = false, angle = Float.MAX_VALUE
        direction: String,
        reqId: Int = 0,
        speed: Float = 0.0f,
        distance: Float = Float.MAX_VALUE,
        avoid: Boolean = false,
        angle: Float = Float.MAX_VALUE,
    ) {
        when (direction) {
            "forward" -> robotApi.goForward(reqId, speed, distance, avoid, mMotionListener)
            "backward" -> robotApi.goBackward(reqId, speed, distance, mMotionListener)
            "turn_left" -> robotApi.turnLeft(reqId, speed, angle, mMotionListener)
            "turn_right" -> robotApi.turnRight(reqId, speed, angle, mMotionListener)
            "stop" -> robotApi.stopMove(reqId, mMotionListener)
        }
        Log.i(TAG, "[MOVE] Basic $direction")
    }

    // 호 운동 - 설명 불명확 기능, 테스트 필요
    fun arcMotion(
        mode: String,
        reqId: Int = 0,
        lineSpeed: Float = 0.5f,
        angularSpeed: Float = 0f,
    ) {
        // motionArcWithObstacles: 장애물 감지?
        when (mode) {
            "non" -> robotApi.motionArc(reqId, lineSpeed, angularSpeed, mMotionListener)
            "obstacle" -> robotApi.motionArcWithObstacles(reqId, lineSpeed, angularSpeed, mMotionListener)
        }
        Log.i(TAG, "[MOVE] Arc $mode")
    }

    // 위치 관련
    fun location(
        mode: String,
        reqId: Int = 0,
        placeName: String,
    ) {
        // estimate point: receoption point(접수처)
        when (mode) {
            "set" -> robotApi.setLocation(reqId, placeName, mMotionListener)
            "get" -> robotApi.getLocation(reqId, placeName, mMotionListener)
            "remove" -> robotApi.removeLocation(reqId, placeName, mMotionListener)
            "init" -> robotApi.setPoseEstimate(reqId, placeName, mMotionListener)
            "isInit" -> robotApi.isRobotEstimate(reqId, mMotionListener)
            "current" -> robotApi.getPosition(reqId, mMotionListener)
            "map" -> robotApi.mapName
            "switch" -> robotApi.switchMap(reqId, placeName, mMotionListener)
            "isPos" -> robotApi.isRobotInlocations(reqId, placeName, mMotionListener)
        }
        Log.i(TAG, "[POS] Location $mode")
    }

    // 탐색
    fun navigation(
        mode: String,
        reqId: Int = 0,
        destination: String = "",
        coordinate: String = "",
    ) {
        when (mode) {
            "start" -> robotApi.startNavigation(reqId, destination, 1.5, 10 * 1000, mNavigationListener)
            "stop" -> robotApi.stopNavigation(reqId)
            "goPos" -> robotApi.goPosition(reqId, coordinate, mMotionListener)
            "stopPos" -> robotApi.stopGoPosition(reqId)
            "toPos" -> robotApi.resumeSpecialPlaceTheta(reqId, destination, mMotionListener)
//            "calc" -> robotApi.getNaviPathInfo(reqId, start, destination, mMotionListener)
        }
        Log.i(TAG, "[NAV] Navigate $mode")
    }

    fun charge(
        mode: String,
        reqId: Int = 0,
        timeout: Long = 3 * Definition.MINUTE,
        isReset: Boolean = false,
    ) {
        when (mode) {
            "start" -> robotApi.startNaviToAutoChargeAction(reqId, timeout, mNavigationListener)
            "stop" -> robotApi.stopAutoChargeAction(reqId, isReset)
            "leave" -> robotApi.stopChargingByApp()
            "standBy" -> robotApi.robotStandby(reqId, mMotionListener)
            "wake" -> robotApi.robotStandbyEnd(reqId)
        }
        Log.i(TAG, "[CAG] Charge $mode")
    }

    // TODO: 리스너 분리
    private val mMotionListener: CommandListener = object : CommandListener() {
        @Deprecated("Deprecated in Java")
        override fun onResult(result: Int, message: String) {
            Log.i("AiniRepo", "result: $result message:$message")
            if ("succeed" == message) {
            } else {
            }
        }
    }

    private val mNavigationListener: ActionListener = object : ActionListener() {}

    companion object {
        private const val TAG = "RobotRepo"
    }
}