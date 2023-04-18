package com.clobot.mini.repo.robot

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ainirobot.coreservice.client.Definition
import com.ainirobot.coreservice.client.RobotApi
import com.ainirobot.coreservice.client.actionbean.LeadingParams
import com.ainirobot.coreservice.client.actionbean.Pose
import com.ainirobot.coreservice.client.listener.ActionListener
import com.ainirobot.coreservice.client.listener.CommandListener
import com.ainirobot.coreservice.client.robotsetting.RobotSettingApi
import com.clobot.mini.data.robot.*
import com.clobot.mini.repo.RobotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AiniRobotRepository @Inject constructor() : RobotRepository {
    private val robotApi = RobotApi.getInstance()
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

    // system 으로 구분 예정
    override fun getVersion(): String {
        return robotApi.version
    }

    // system or charge 로 구분 예정
    fun getBattery(): String {
        return "Battery level:" +
                RobotSettingApi.getInstance()
                    .getRobotString(Definition.ROBOT_SETTINGS_BATTERY_INFO) + "%"
    }

    // 기본 동작
    fun basicMotion(
        // reqId = 0, speed = 0.0f, distance = Float.MAX_VALUE, avoid = false, angle = Float.MAX_VALUE
        direction: MoveDirection,
        reqId: Int = 0,
        speed: Float = 0.2f,
        distance: Float = Float.MAX_VALUE,
        avoid: Boolean = false,
        angle: Float = Float.MAX_VALUE,
    ) {
        when (direction) {
            MoveDirection.Forward -> robotApi.goForward(reqId, speed, distance, avoid, mMotionListener)
            MoveDirection.Backward -> robotApi.goBackward(reqId, speed, distance, mMotionListener)
            MoveDirection.TurnLeft -> robotApi.turnLeft(reqId, speed, angle, mMotionListener)
            MoveDirection.TurnRight -> robotApi.turnRight(reqId, speed, angle, mMotionListener)
            MoveDirection.Stop -> robotApi.stopMove(reqId, mMotionListener)
        }
        Log.i(TAG, "[$MTN] Basic $direction")
    }

    // 호 운동 - 설명 불명확 기능, 테스트 필요
    fun arcMotion(
        mode: ArcMode,
        reqId: Int = 0,
        lineSpeed: Float = 0.5f,
        angularSpeed: Float = 0f,
    ) {
        // motionArcWithObstacles: 장애물 감지?
        when (mode) {
            ArcMode.None -> robotApi.motionArc(reqId, lineSpeed, angularSpeed, mMotionListener)
            ArcMode.Obstacle -> robotApi.motionArcWithObstacles(reqId, lineSpeed, angularSpeed, mMotionListener)
        }
        Log.i(TAG, "[$MTN] Arc $mode")
    }

    // 위치
    fun position(
        mode: PosMode,
        reqId: Int = 0,
        placeName: String = "",
    ) {
        // estimate point: receoption point(접수처)
        when (mode) {
            PosMode.Set -> robotApi.setLocation(reqId, placeName, mMotionListener)
            PosMode.Get -> robotApi.getLocation(reqId, placeName, mMotionListener)
            PosMode.Pos -> robotApi.getPosition(reqId, mMotionListener)
            PosMode.Remove -> robotApi.removeLocation(reqId, placeName, mMotionListener)
            PosMode.Init -> robotApi.setPoseEstimate(reqId, placeName, mMotionListener)
            PosMode.IsInit -> robotApi.isRobotEstimate(reqId, mMotionListener)
            PosMode.Switch -> robotApi.switchMap(reqId, placeName, mMotionListener)
            PosMode.IsPos -> robotApi.isRobotInlocations(reqId, placeName, mMotionListener)
            PosMode.MapName -> robotApi.getMapName(reqId, mMotionListener)
            PosMode.List -> robotApi.placeList
        }
        Log.i(TAG, "[$POS] Location $mode")
    }

    // 탐색
    fun navigation(
        mode: NavMode,
        reqId: Int = 0,
        destination: String = "",
        coordinate: String = "",
        route: List<Pose> = emptyList(),
        dockingPoints: List<Int> = emptyList(),
    ) {
        when (mode) {
            NavMode.Start -> robotApi.startNavigation(reqId, destination, 1.5, 10 * 1000, mNavigationListener)
            NavMode.Stop -> robotApi.stopNavigation(reqId)
            NavMode.GoPos -> robotApi.goPosition(reqId, coordinate, mMotionListener)
            NavMode.StopPos -> robotApi.stopGoPosition(reqId)
            NavMode.ToPos -> robotApi.resumeSpecialPlaceTheta(reqId, destination, mMotionListener)
            NavMode.Cruise -> robotApi.startCruise(reqId, route, 0, dockingPoints, mNavigationListener)
            NavMode.Lead -> robotApi.startLead(reqId, LeadingParams(), mNavigationListener)
            NavMode.StopLead -> robotApi.stopLead(reqId, true)
//            "calc" -> robotApi.getNaviPathInfo(reqId, start, destination, mMotionListener)
        }
        Log.i(TAG, "[$NAV] Navigate $mode")
    }

    // 충전
    fun charge(
        mode: ChargeMode,
        reqId: Int = 0,
        timeout: Long = 3 * Definition.MINUTE,
        isReset: Boolean = false,
    ) {
        when (mode) {
            ChargeMode.Start -> robotApi.startNaviToAutoChargeAction(reqId, timeout, mNavigationListener)
            ChargeMode.Stop -> robotApi.stopAutoChargeAction(reqId, isReset)
            ChargeMode.Leave -> robotApi.leaveChargingPile(reqId, 0.5f, 0.5f, mMotionListener)
            ChargeMode.StopLeave -> robotApi.stopChargingByApp()
            ChargeMode.Standby -> robotApi.robotStandby(reqId, mMotionListener)
            ChargeMode.Wake -> robotApi.robotStandbyEnd(reqId)
        }
        Log.i(TAG, "[$CHG] Charge $mode")
    }

    // 동작 리스너
    private val mMotionListener: CommandListener = object : CommandListener() {
        override fun onResult(result: Int, message: String, extraData: String) {
            Log.i("AiniRepo", "result: $result message:$message")
            if ("succeed" == message) {
            } else {
            }
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Log.i("AiniRepo", "status: $status data:$data")
        }
    }

    // 탐색 리스너
    private val mNavigationListener: ActionListener = object : ActionListener() {
        override fun onResult(status: Int, response: String, extraData: String) {
            Log.i("AiniRepo", "status: $status response:$response")
        }

        override fun onError(code: Int, message: String, extraData: String) {
            Log.i("AiniRepo", "code: $code message:$message")
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Log.i("AiniRepo", "status: $status data:$data")
        }
    }

    companion object {
        private const val TAG = "RobotRepo"

        private const val MTN = "Motion"
        private const val POS = "Position"
        private const val NAV = "Navigation"
        private const val CHG = "Charge"
        private const val QR = "QR"
        private const val SYS = "System"
    }
}