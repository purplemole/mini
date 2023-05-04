package com.clobot.mini.model

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainirobot.coreservice.client.Definition
import com.ainirobot.coreservice.client.StatusListener
import com.ainirobot.coreservice.client.listener.ActionListener
import com.ainirobot.coreservice.client.listener.CommandListener
import com.clobot.mini.data.robot.*
import com.clobot.mini.repo.robot.AiniRobotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RobotViewModel @Inject constructor(
    private val repo: AiniRobotRepository,
//    private val ttsRepo: AiniTtsRepository
) : ViewModel() {
    private val _dockingState = MutableStateFlow(false)
    val dockingState: StateFlow<Boolean> = _dockingState.asStateFlow()

    private val _chargingState = MutableStateFlow(false)
    val chargingState: StateFlow<Boolean> = _chargingState.asStateFlow()

    private val _placeList = MutableStateFlow<List<String>>(ArrayList())
    val placeList = _placeList.asStateFlow()

    private val _moveReason = MutableStateFlow<MoveReason>(MoveReason.None)
    val moveReason = _moveReason.asStateFlow()

    private val _textLog = MutableStateFlow("")
    val textLog: StateFlow<String> = _textLog.asStateFlow()

    private var reqId = 0

    init {
        viewModelScope.launch {
            repo.initialize().let { it ->
                repo.dockingState.collectLatest {
                    _dockingState.emit(it)
                }
            }
        }
    }

    fun changeMoveReason(newReason: MoveReason) {
        _moveReason.value = newReason
    }

    fun refreshPlaceList() = viewModelScope.launch {
        runCatching {
            repo.location("List", listener = mLocationListener)
        }.onSuccess {
            _placeList.emit(_placeList.value)
        }
    }

    fun checkChargingState() = viewModelScope.launch {
        repo.checkChargingStation()
        repo.chargingState.collect {
            _chargingState.emit(it)
        }
    }

    fun checkMoveState() = viewModelScope.launch {
        _moveReason.collect {
            _moveReason.emit(it)
        }
    }

    fun basicMotion(
        direction: MoveDirection,
    ) {
        repo.basicMotion(direction, mMotionListener)
    }

    fun headMotion(
        mode: String
    ) {
        repo.headMotion(mode, mMotionListener, reqId++)
    }

    fun posController(
        mode: PosMode,
    ) {
        repo.position(mode, mMotionListener)
    }

    fun locController(
        mode: String,
    ) {
        repo.location(mode, mMotionListener)
    }

    fun navController(
        mode: NavMode,
        dest: String = "",
    ) {
        _moveReason.value = MoveReason.Guide

        repo.navigation(mode, mNavigationListener, dest = dest)
    }

    fun moveByReason(
        mode: MoveReason,
        dest: String = "",
    ) {
        // 화면 전환을 위한 state
        _moveReason.value = mode

        when (mode) {
            // 초기 위치로 이동(안내 대기 장소)
            MoveReason.Home -> repo.navigation(NavMode.Start, mNavigationListener, dest = "안내 대기 장소")
            // 충전대로 이동
            MoveReason.Docking -> repo.charge(ChargeMode.Auto, mNavigationListener)
            // 해당 위치로 이동
            MoveReason.Guide -> repo.navigation(NavMode.Start, mNavigationListener, dest = dest)
            else -> {}
        }
    }

    fun mapController(
        mode: String,
    ) {
        repo.map(mode, mMotionListener)
    }

    fun chargeController(
        mode: ChargeMode,
    ) {
        if (mode == ChargeMode.Auto)
            _moveReason.value = MoveReason.Guide

        repo.charge(mode, mNavigationListener)
    }

    fun ttsController(
        mode: TtsMode,
        text: String = ""
    ) {
//        ttsRepo.tts(mode, text)
    }

    fun autoCharge() {
        when (repo.getBattery()) {
            20 -> chargeController(ChargeMode.Start)
            50 -> Timber.d("charge level: 50%")
            100 -> chargeController(ChargeMode.StopLeave)
        }
    }

    fun systemController(
        mode: String
    ) {
        repo.setting(mode)
    }

    /* Listener Dummy */
    // 동작
    private val mMotionListener: CommandListener = object : CommandListener() {
        override fun onResult(result: Int, message: String, extraData: String) {
            Timber.i("result: $result message:$message")
            _textLog.value = message
//                when (result) {
//                    Definition.RESULT_OK -> // 도착 완료
//                    Definition.RESULT_FAILURE -> // 도착 실패
//                }
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Timber.i("status: $status data:$data")
        }
    }

    // 탐색
    private val mNavigationListener: ActionListener = object : ActionListener() {
        override fun onResult(status: Int, response: String) {
            Timber.i("status: $status response:$response")
            _textLog.value = status.toString()
            when (status) {
                Definition.RESULT_OK -> _moveReason.value = MoveReason.None
                else -> {}
            }
        }

        override fun onError(code: Int, message: String, extraData: String) {
            Timber.i("code: $code message:$message")
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Timber.i("status: $status data:$data")
        }
    }

    // 위치
    private val mLocationListener: CommandListener = object : CommandListener() {
        override fun onResult(result: Int, message: String, extraData: String) {
            Timber.i("result: $result message:$message")
//            _textLog.value = message
            try {
                val placeList: MutableList<String> = ArrayList()
                placeList.clear()
                val jsonArray = JSONArray(message)
                val length: Int = jsonArray.length()
                for (i in 0 until length) {
                    val json: JSONObject = jsonArray.getJSONObject(i)
                    placeList.add(json.getString("name"))
                }
                _placeList.value = placeList
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Timber.i("status: $status data:$data")
        }
    }

    // 상태
    private val mStatusListener: StatusListener = object : StatusListener() {
        override fun onStatusUpdate(type: String, data: String) {
            Timber.i("type: $type data:$data")
            _textLog.value = data
        }
    }
}