package com.clobot.mini.repo.robot

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ainirobot.coreservice.client.Definition
import com.ainirobot.coreservice.client.RobotApi
import com.ainirobot.coreservice.client.StatusListener
import com.ainirobot.coreservice.client.actionbean.LeadingParams
import com.ainirobot.coreservice.client.actionbean.Pose
import com.ainirobot.coreservice.client.listener.ActionListener
import com.ainirobot.coreservice.client.listener.CommandListener
import com.ainirobot.coreservice.client.robotsetting.RobotSettingApi
import com.clobot.mini.data.robot.*
import com.clobot.mini.repo.RobotRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class AiniRobotRepository @Inject constructor() : RobotRepository {
    private val robotApi = RobotApi.getInstance()
    private val _dockingState = MutableStateFlow(false)
    override val dockingState: StateFlow<Boolean> = _dockingState

    private var checkTimes: Int = 0

    // 지점 리스트
    val placeList: MutableList<String> = ArrayList()

    override fun initialize(): Boolean {
        checkTimes++
        return if (checkTimes > 10) {
            false
        } else if (robotApi.isApiConnectedService
            && robotApi.isActive
        ) {
            // 도킹스테이션 확인
            _dockingState.value = robotApi.isChargePileExits
            true
        } else {
            Handler(Looper.getMainLooper()).postDelayed(Runnable { initialize() }, 300)
        }
    }

    fun checkDockingStation() {
        _dockingState.value = robotApi.isChargePileExits
    }

    // system 으로 구분 예정
    fun getVersion(): String {
        return robotApi.version
    }

    // system or charge 로 구분 예정
    fun getBattery(): Int {
        return RobotSettingApi.getInstance().getRobotString(Definition.ROBOT_SETTINGS_BATTERY_INFO).toInt()
    }

    // 기본 동작
    fun basicMotion(
        // reqId = 0, speed = 0.0f, distance = Float.MAX_VALUE, avoid = false, angle = Float.MAX_VALUE
        direction: MoveDirection,
        reqId: Int = 0,
        speed: Float = 0.2f,
        angleSpeed: Float = 15.0f,
        distance: Float = 1f,
        avoid: Boolean = false,
        angle: Float = 90f,
    ) {
        when (direction) {
            MoveDirection.Forward -> robotApi.goForward(reqId, speed, distance, avoid, mMotionListener)
            MoveDirection.Backward -> robotApi.goBackward(reqId, speed, distance, mMotionListener)
            MoveDirection.TurnLeft -> robotApi.turnLeft(reqId, angleSpeed, angle, mMotionListener)
            MoveDirection.TurnRight -> robotApi.turnRight(reqId, angleSpeed, angle, mMotionListener)
            MoveDirection.Stop -> robotApi.stopMove(reqId, mMotionListener)
            // 테스트
            MoveDirection.AllStop -> robotApi.stopAllAction(reqId)
            MoveDirection.TurnToTarget -> robotApi.turnToTargetDirection(reqId, RobotApi.getInstance().placeList.first(), 0.2, 1.0, false, mMotionListener)
            MoveDirection.StopTurnToTarget -> robotApi.stopTurnToTargetDirection(reqId)
        }
        Log.i(TAG, "[$MTN] Basic $reqId")
    }

    // 호 운동 - 설명 불명확 기능, 테스트 필요
    fun arcMotion(
        mode: ArcMode,
        reqId: Int = 0,
        lineSpeed: Float = 0.2f,
        angularSpeed: Float = 1.0f,
    ) {
        // motionArcWithObstacles: 장애물 감지?
        when (mode) {
            ArcMode.None -> robotApi.motionArc(reqId, lineSpeed, angularSpeed, mMotionListener)
            ArcMode.Obstacle -> robotApi.motionArcWithObstacles(reqId, lineSpeed, angularSpeed, mMotionListener)
        }
        Log.i(TAG, "[$MTN] Arc $mode")
    }

    fun headMotion(
        mode: String,
        reqId: Int = 0,
        hori: Int = -10,
        vert: Int = 0,
    ) {
        when (mode) {
            "Reset" -> robotApi.resetHead(reqId, mMotionListener)
            "Move" -> robotApi.moveHead(reqId, "relative", "relative", hori, vert, mMotionListener)
            // 테스트
            "Stop" -> robotApi.stopTurnHead(reqId, mMotionListener)
            "Status" -> robotApi.getHeadStatus(reqId, mMotionListener)
            "Wake" -> robotApi.wakeUp(reqId, 0.2f, true, mNavigationListener)
            "StopWake" -> robotApi.stopWakeUp(reqId)
//            "Track" -> robotApi.startFocusFollow(reqId, )
        }
        Log.i(TAG, "[$MTN] Head $mode")
    }

    // 탐색
    fun navigation(
        mode: NavMode,
        reqId: Int = 0,
        destination: String = "",
        speed: Double = 0.2,
        angleSpeed: Double = 1.0,
    ) {
        when (mode) {
            NavMode.Start -> robotApi.startNavigation(reqId, destination, 1.5, 10 * 1000, speed, angleSpeed, mNavigationListener)
            NavMode.Stop -> robotApi.stopNavigation(reqId)
            NavMode.ToPos -> robotApi.resumeSpecialPlaceTheta(reqId, destination, mMotionListener)
            NavMode.StopToPos -> robotApi.stopResumeSpecialPlaceThetaAction(reqId)
            // 테스트
            NavMode.Lead -> robotApi.startLead(reqId, LeadingParams(), mNavigationListener)
            NavMode.StopLead -> robotApi.stopLead(reqId, true)
            NavMode.PosNavi -> robotApi.startPoseNavigation(reqId, destination, 1.5, 10 * 1000, speed, angleSpeed, true, mNavigationListener)
            NavMode.StopPosNavi -> robotApi.stopPoseNavigation(reqId)
            NavMode.NaviBack -> robotApi.startNavigationBack(reqId, destination, speed, angleSpeed, mNavigationListener)
            NavMode.StopNaviBack -> robotApi.stopNavigationBack(reqId)
            NavMode.SetConfig -> robotApi.setNavigationConfig(reqId, "", mMotionListener)
            NavMode.Config -> robotApi.getNavigationConfig(reqId, mMotionListener)
            NavMode.Status -> robotApi.isInNavigation(reqId, mMotionListener)
        }
        Log.i(TAG, "[$NAV] Navigate $mode")
    }

    // 순항
    fun cruise(
        mode: Cruise,
        reqId: Int = 0,
        route: List<Pose> = emptyList(),
        dockingPoints: List<Int> = emptyList(),
    ) {
        when (mode) {
            Cruise.Start -> robotApi.startCruise(reqId, route, 0, dockingPoints, mNavigationListener)
            Cruise.Stop -> robotApi.stopCruise(reqId)
            Cruise.Status -> robotApi.postCruiseStatus(reqId, "")
            Cruise.Clear -> robotApi.clearNaviCruiseRoute(reqId, "", mMotionListener)
        }
        Log.i(TAG, "[$NAV] Cruise $mode")
    }

    // 위치
    fun position(
        mode: PosMode,
        reqId: Int = 0,
        coordinate: String = "",
    ) {
        when (mode) {
            PosMode.Pos -> robotApi.getPosition(reqId, mMotionListener)
            PosMode.GoPos -> robotApi.goPosition(reqId, coordinate, mMotionListener)
            PosMode.StopPos -> robotApi.stopGoPosition(reqId)
            // Mapping
        }
        Log.i(TAG, "[$POS] Position $mode")
    }

    fun location(
        mode: String,
        reqId: Int = 0,
        placeName: String = "",
    ) {
        when (mode) {
            "Set" -> robotApi.setLocation(reqId, placeName, mMotionListener)
            "Get" -> robotApi.getLocation(reqId, placeName, mMotionListener)
            "Remove" -> robotApi.removeLocation(reqId, placeName, mMotionListener)
            "Edit" -> robotApi.editPlace(reqId, "", mMotionListener)
            "List" -> robotApi.getPlaceList(reqId, mLocationListener)
            "ListPos" -> robotApi.placeList
            "IsPos" -> robotApi.isRobotInlocations(placeName, 0.0)
            // estimate point: receoption point(접수처)
            "Init" -> robotApi.setPoseEstimate(reqId, "", mMotionListener)
            "Fix" -> robotApi.setFixedEstimate(reqId, "", mMotionListener)
            "Reset" -> robotApi.resetPoseEstimate(reqId, mMotionListener)
            "IsInit" -> robotApi.isRobotEstimate
            "IsRec" -> robotApi.isInReceptionLocation
            // distance
            "Dist" -> robotApi.getPlaceDistance(placeName)
            "SafeDist" -> robotApi.setObstaclesSafeDistance(reqId, 0.5, mMotionListener)
            "resetDist" -> robotApi.resetObstaclesSafeDistance(reqId, mMotionListener)
        }
        Log.i(TAG, "[$POS] Location $mode")
    }

    fun map(
        mode: String,
        reqId: Int = 0,
//        Bean: StartCreateMapBean,
    ) {
        when (mode) {
            "Name" -> robotApi.getMapName(reqId, mMotionListener)
            "NameS" -> robotApi.mapName
            "Rename" -> robotApi.renameMap(reqId, "", "", mMotionListener)
            // setMapInfo
//            "Create" -> robotApi.startCreatingMap(reqId, Bean, mMotionListener)
            "StopCreate" ->  robotApi.stopCreatingMap(reqId, "", mMotionListener)
            "CancelCreate" -> robotApi.cancelCreateMap(reqId, mMotionListener)
            "Remove" -> robotApi.removeMap(reqId, "", mMotionListener)
            "Switch" -> robotApi.switchMap(reqId, "", mMotionListener)
            "Load" -> robotApi.loadCurrentMap(reqId, false, mMotionListener)
            "Clear" -> robotApi.clearCurNaviMap(reqId, mMotionListener)
            "List" -> robotApi.getLocalMapInfoList(reqId, mMotionListener)
        }
        Log.i(TAG, "[$POS] Map $mode")
    }

    // 충전
    fun charge(
        mode: ChargeMode,
        reqId: Int = 0,
        timeout: Long = 3 * Definition.MINUTE,
        isReset: Boolean = false,
    ) {
        when (mode) {
            ChargeMode.GoCharge -> robotApi.goCharging(reqId)
            ChargeMode.Start -> robotApi.startCharge(reqId)
            ChargeMode.Stop -> robotApi.stopCharge(reqId)
            ChargeMode.Auto -> robotApi.startNaviToAutoChargeAction(reqId, timeout, mNavigationListener)
            ChargeMode.StopAuto -> robotApi.stopAutoChargeAction(reqId, isReset)
            ChargeMode.Leave -> robotApi.leaveChargingPile(reqId, 0.5f, 0.5f, mMotionListener)
            ChargeMode.StopLeave -> robotApi.stopChargingByApp()
            // Status
            ChargeMode.Exits -> robotApi.isChargePileExits
            ChargeMode.Status -> robotApi.chargeStatus
            ChargeMode.Level -> robotApi.batteryLevel
            ChargeMode.Door -> robotApi.getDoorStatus(reqId, 0, mMotionListener)
            ChargeMode.RemainBattTime -> robotApi.getBatteryTimeRemaining(reqId, mMotionListener)
            ChargeMode.RemainChargeTime -> robotApi.getChargeTimeRemaining(reqId, mMotionListener)
        }
        Log.i(TAG, "[$CHG] Charge $mode")
    }

    fun setting(
        mode: String,
        reqId: Int = 0,
    ) {
        when (mode) {
            "Lock" -> robotApi.setLockEnable(reqId, 0, 0, true)
            "DisEmerg" -> robotApi.disableEmergency()
            "EnEmerg" -> robotApi.enableEmergency()
            "DisBatt" -> robotApi.disableBattery()
            "EnBatt" -> robotApi.enableBattery()
            "ResetSystem" -> robotApi.resetSystemStatus()
            "Reboot" -> robotApi.canRobotReboot(reqId, mMotionListener)
            "Standby" -> robotApi.robotStandby(reqId, mMotionListener)
            "Wake" -> robotApi.robotStandbyEnd(reqId)
            // Status
            "FullCheck" -> robotApi.getFullCheckStatus(reqId, mMotionListener)
            "Emerg" -> robotApi.getEmergencyStatus(reqId, mMotionListener)
            "Status" -> robotApi.getRobotStatus("", mStatusListener)
            "Update" -> robotApi.updateRobotStatus(0)
        }
        Log.i(TAG, "[$SYS] Setting $mode")
    }

    // 동작 리스너
    private val mMotionListener: CommandListener = object : CommandListener() {
        override fun onResult(result: Int, message: String, extraData: String) {
            Log.i(TAG, "result: $result message:$message")
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Log.i(TAG, "status: $status data:$data")
        }
    }

    private val mLocationListener: CommandListener = object : CommandListener() {
        override fun onResult(result: Int, message: String, extraData: String) {
            Log.i(TAG, "result: $result message:$message")
            try {
                placeList.clear()
                val jsonArray = JSONArray(message)
                val length: Int = jsonArray.length()
                for (i in 0 until length) {
                    val json: JSONObject = jsonArray.getJSONObject(i)
                    placeList.add(json.getString("name"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Log.i(TAG, "status: $status data:$data")
        }
    }

    // 탐색 리스너
    private val mNavigationListener: ActionListener = object : ActionListener() {
        override fun onResult(status: Int, response: String) {
            Log.i(TAG, "status: $status response:$response")
        }

        override fun onError(code: Int, message: String, extraData: String) {
            Log.i(TAG, "code: $code message:$message")
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Log.i(TAG, "status: $status data:$data")
        }
    }

    private val mStatusListener: StatusListener = object : StatusListener() {
        override fun onStatusUpdate(type: String, data: String) {
            Log.i(TAG, "type: $type data:$data")
        }
    }

    companion object {
        private const val TAG = "RobotRepo"

        private const val MTN = "Motion"
        private const val POS = "Position"
        private const val NAV = "Navigation"
        private const val CHG = "Charge"
        private const val QR = "QR"
        private const val TTS = "TTS"
        private const val SYS = "System"
    }
}