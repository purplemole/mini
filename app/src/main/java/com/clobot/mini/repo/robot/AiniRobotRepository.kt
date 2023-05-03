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
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

class AiniRobotRepository @Inject constructor() : RobotRepository {
    private val robotApi = RobotApi.getInstance()
    private val settingApi = RobotSettingApi.getInstance()

    private val _dockingState = MutableStateFlow(false)
    override val dockingState: StateFlow<Boolean> = _dockingState.asStateFlow()

    private val _chargingState = MutableStateFlow(false)
    val chargingState: StateFlow<Boolean> = _chargingState.asStateFlow()

    private var checkTimes: Int = 0
//    val battState = robotApi.chargeStatus

    override fun initialize(): Boolean {
        checkTimes++
        return if (checkTimes > 10) {
            false
        } else if (robotApi.isApiConnectedService
            && robotApi.isActive
        ) {
            // 도킹스테이션 확인
            _dockingState.value = robotApi.chargeStatus ||
                    (settingApi.getRobotString(Definition.CHARGING_PILE_CONFIGURED) == "1"
                            && robotApi.getPlaceDistance("충전대") != 0.0)
            robotApi.disableBattery()
            robotApi.disableEmergency()
            true
        } else {
            Handler(Looper.getMainLooper()).postDelayed(Runnable { initialize() }, 300)
        }
    }


    /** 충전 상태 확인
     * TODO NULL check
     * emulator : robotApi.chargeStatue (NullPointerException)
     */
    fun checkChargingStation() {
        try {
            if (_chargingState.value != robotApi.chargeStatus)
                _chargingState.value = robotApi.chargeStatus
        } catch (e: NullPointerException) {
            return
        }
    }

    // system 으로 구분 예정
    fun getVersion(): String {
        return robotApi.version
    }

    // system or charge 로 구분 예정
    fun getBattery(): Int {
        return RobotSettingApi.getInstance().getRobotString(Definition.ROBOT_SETTINGS_BATTERY_INFO)
            .toInt()
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
        listener: CommandListener
    ) {
        when (direction) {
            MoveDirection.Forward -> robotApi.goForward(reqId, speed, distance, avoid, listener)
            MoveDirection.Backward -> robotApi.goBackward(reqId, speed, distance, listener)
            MoveDirection.TurnLeft -> robotApi.turnLeft(reqId, angleSpeed, angle, listener)
            MoveDirection.TurnRight -> robotApi.turnRight(reqId, angleSpeed, angle, listener)
            MoveDirection.Stop -> robotApi.stopMove(reqId, listener)
            // 테스트
            MoveDirection.AllStop -> robotApi.stopAllAction(reqId)
            MoveDirection.TurnToTarget -> robotApi.turnToTargetDirection(
                reqId,
                RobotApi.getInstance().placeList.first(),
                0.2,
                1.0,
                false,
                listener
            )
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
        listener: CommandListener
    ) {
        // motionArcWithObstacles: 장애물 감지?
        when (mode) {
            ArcMode.None -> robotApi.motionArc(reqId, lineSpeed, angularSpeed, listener)
            ArcMode.Obstacle -> robotApi.motionArcWithObstacles(
                reqId,
                lineSpeed,
                angularSpeed,
                listener
            )
        }
        Log.i(TAG, "[$MTN] Arc $mode")
    }

    fun headMotion(
        mode: String,
        reqId: Int = 0,
        hori: Int = -10,
        vert: Int = 0,
        listener: CommandListener
    ) {
        when (mode) {
            "Reset" -> robotApi.resetHead(reqId, listener)
            "Move" -> robotApi.moveHead(reqId, "relative", "relative", hori, vert, listener)
            // 테스트
            "Stop" -> robotApi.stopTurnHead(reqId, listener)
            "Status" -> robotApi.getHeadStatus(reqId, listener)
//            "Wake" -> robotApi.wakeUp(reqId, 0.2f, true, mNavigationListener)
//            "StopWake" -> robotApi.stopWakeUp(reqId)
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
        listener: ActionListener
    ) {
        when (mode) {
            NavMode.Start -> robotApi.startNavigation(
                reqId,
                destination,
                1.5,
                10 * 1000,
                speed,
                angleSpeed,
                listener
            )
            NavMode.Stop -> robotApi.stopNavigation(reqId)
//            NavMode.ToPos -> robotApi.resumeSpecialPlaceTheta(reqId, destination, mMotionListener)
            NavMode.StopToPos -> robotApi.stopResumeSpecialPlaceThetaAction(reqId)
            // 테스트
            NavMode.Lead -> robotApi.startLead(reqId, LeadingParams(), listener)
            NavMode.StopLead -> robotApi.stopLead(reqId, true)
            NavMode.PosNavi -> robotApi.startPoseNavigation(
                reqId,
                destination,
                1.5,
                10 * 1000,
                speed,
                angleSpeed,
                true,
                listener
            )
            NavMode.StopPosNavi -> robotApi.stopPoseNavigation(reqId)
//            NavMode.NaviBack -> robotApi.startNavigationBack(reqId, destination, speed, angleSpeed, mNavigationListener)
//            NavMode.StopNaviBack -> robotApi.stopNavigationBack(reqId)
//            NavMode.SetConfig -> robotApi.setNavigationConfig(reqId, "", mMotionListener)
//            NavMode.Config -> robotApi.getNavigationConfig(reqId, mMotionListener)
//            NavMode.Status -> robotApi.isInNavigation(reqId, mMotionListener)
        }
        Log.i(TAG, "[$NAV] Navigate $mode")
    }

    // 순항
    fun cruise(
        mode: Cruise,
        reqId: Int = 0,
        route: List<Pose> = emptyList(),
        dockingPoints: List<Int> = emptyList(),
        listener: ActionListener
    ) {
        when (mode) {
            Cruise.Start -> robotApi.startCruise(reqId, route, 0, dockingPoints, listener)
            Cruise.Stop -> robotApi.stopCruise(reqId)
            Cruise.Status -> robotApi.postCruiseStatus(reqId, "")
//            Cruise.Clear -> robotApi.clearNaviCruiseRoute(reqId, "", mMotionListener)
        }
        Log.i(TAG, "[$NAV] Cruise $mode")
    }

    // 위치
    fun position(
        mode: PosMode,
        reqId: Int = 0,
        coordinate: String = "",
        listener: CommandListener
    ) {
        when (mode) {
            PosMode.Pos -> robotApi.getPosition(reqId, listener)
            PosMode.GoPos -> robotApi.goPosition(reqId, coordinate, listener)
            PosMode.StopPos -> robotApi.stopGoPosition(reqId)
            // Mapping
        }
        Log.i(TAG, "[$POS] Position $mode")
    }

    fun location(
        mode: String,
        reqId: Int = 0,
        placeName: String = "",
        listener: CommandListener
    ) {
        when (mode) {
            "Set" -> robotApi.setLocation(reqId, placeName, listener)
            "Get" -> robotApi.getLocation(reqId, placeName, listener)
            "Remove" -> robotApi.removeLocation(reqId, placeName, listener)
            "Edit" -> robotApi.editPlace(reqId, "", listener)
            "List" -> robotApi.getPlaceList(reqId, listener)
            "ListPos" -> robotApi.placeList
            "IsPos" -> robotApi.isRobotInlocations(placeName, 0.0)
            // estimate point: receoption point(접수처)
            "Init" -> robotApi.setPoseEstimate(reqId, "", listener)
            "Fix" -> robotApi.setFixedEstimate(reqId, "", listener)
            "Reset" -> robotApi.resetPoseEstimate(reqId, listener)
            "IsInit" -> robotApi.isRobotEstimate
            "IsRec" -> robotApi.isInReceptionLocation
            // distance
            "Dist" -> Log.d(TAG, robotApi.getPlaceDistance("충전대").toString())
            "SafeDist" -> robotApi.setObstaclesSafeDistance(reqId, 0.5, listener)
            "resetDist" -> robotApi.resetObstaclesSafeDistance(reqId, listener)
        }
        Log.i(TAG, "[$POS] Location $mode")
    }

    fun map(
        mode: String,
        reqId: Int = 0,
        listener: CommandListener
//        Bean: StartCreateMapBean,
    ) {
        when (mode) {
            "Name" -> robotApi.getMapName(reqId, listener)
            "NameS" -> robotApi.mapName
            "Rename" -> robotApi.renameMap(reqId, "", "", listener)
            // setMapInfo
//            "Create" -> robotApi.startCreatingMap(reqId, Bean, mMotionListener)
            "StopCreate" -> robotApi.stopCreatingMap(reqId, "", listener)
            "CancelCreate" -> robotApi.cancelCreateMap(reqId, listener)
            "Remove" -> robotApi.removeMap(reqId, "", listener)
            "Switch" -> robotApi.switchMap(reqId, "", listener)
            "Load" -> robotApi.loadCurrentMap(reqId, false, listener)
            "Clear" -> robotApi.clearCurNaviMap(reqId, listener)
            "List" -> robotApi.getLocalMapInfoList(reqId, listener)
        }
        Log.i(TAG, "[$POS] Map $mode")
    }

    // 충전
    fun charge(
        mode: ChargeMode,
        reqId: Int = 0,
        timeout: Long = 3 * Definition.MINUTE,
        isReset: Boolean = false,
        listener: ActionListener
    ) {
        when (mode) {
            // 충전기로 이동
            ChargeMode.GoCharge -> robotApi.goCharging(reqId)
//            ChargeMode.Start -> robotApi.startCharge(reqId)
//            ChargeMode.Stop -> robotApi.stopCharge(reqId)
            ChargeMode.Auto -> robotApi.startNaviToAutoChargeAction(reqId, timeout, listener)
            ChargeMode.StopAuto -> robotApi.stopAutoChargeAction(reqId, isReset)
//            ChargeMode.Leave -> robotApi.leaveChargingPile(reqId, 0.5f, 0.5f, mMotionListener)
            // 로봇 운영 시작
            ChargeMode.StopLeave -> robotApi.stopChargingByApp()
            // Status
            ChargeMode.Exits -> Log.d(TAG, "${robotApi.isChargePileExits}")
            ChargeMode.Status -> Log.d(TAG, "${robotApi.chargeStatus}")
            ChargeMode.Level -> Log.d(TAG, "${robotApi.batteryLevel}")
//            ChargeMode.Door -> robotApi.getDoorStatus(reqId, 0, mMotionListener)
//            ChargeMode.RemainBattTime -> robotApi.getBatteryTimeRemaining(reqId, mMotionListener)
//            ChargeMode.RemainChargeTime -> robotApi.getChargeTimeRemaining(reqId, mMotionListener)
        }
        Log.i(TAG, "[$CHG] Charge $mode")
    }

    fun setting(
        mode: String,
        reqId: Int = 0,
        listener: CommandListener? = null,
        statusListener: StatusListener? = null
    ) {
        when (mode) {
            "Lock" -> robotApi.setLockEnable(reqId, 0, 0, true)
            "DisEmerg" -> robotApi.disableEmergency()
            "EnEmerg" -> robotApi.enableEmergency()
            "DisBatt" -> robotApi.disableBattery()
            "EnBatt" -> robotApi.enableBattery()
//            "ResetSystem" -> robotApi.resetSystemStatus()
//            "Reboot" -> robotApi.canRobotReboot(reqId, mMotionListener)
//            "Standby" -> robotApi.robotStandby(reqId, mBackMotionListener)
//            "Wake" -> robotApi.robotStandbyEnd(reqId)
            // Status
//            "FullCheck" -> robotApi.getFullCheckStatus(reqId, mMotionListener)
            "Emerg" -> robotApi.getEmergencyStatus(reqId, listener)
            "Status" -> robotApi.getRobotStatus(Definition.STATUS_EMERGENCY, statusListener)
//            "Update" -> robotApi.updateRobotStatus(0)
        }
        Log.i(TAG, "[$SYS] Setting $mode")
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