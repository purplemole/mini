package com.clobot.mini.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import javax.inject.Inject

@HiltViewModel
class RobotViewModel @Inject constructor(
    private val repo: AiniRobotRepository,
//    private val ttsRepo: AiniTtsRepository
) : ViewModel() {
    private val _dockingState = MutableStateFlow<Boolean>(false)
    val dockingState: StateFlow<Boolean> = _dockingState.asStateFlow()

    private val _placeList = MutableStateFlow<List<String>>(ArrayList())
    val placeList = _placeList.asStateFlow()

    private val _moveReason = mutableStateOf<MoveReason>(MoveReason.None)
    val moveReason = _moveReason.value//: State<MoveReason> = _moveReason

    private var reqId = 0

    init {
        viewModelScope.launch {
            repo.initialize().let {
//                repo.dockingState.collectLatest {
//                    _dockingState.emit(it)
//                }
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

    fun checkDockingState() = viewModelScope.launch {
        repo.checkDockingStation()
        repo.dockingState.collect {
            _dockingState.emit(it)
        }
    }

    fun basicMotion(
        direction: MoveDirection,
    ) {
        repo.basicMotion(direction, listener = mMotionListener)
    }

    fun arcMotion(
        mode: ArcMode,
    ) {
        repo.arcMotion(mode, listener = mMotionListener)
    }

    fun headMotion(
        mode: String
    ) {
        repo.headMotion(mode, reqId++, listener = mMotionListener)
    }

    fun posController(
        mode: PosMode,
    ) {
        repo.position(mode, listener = mMotionListener)
    }

    fun locController(
        mode: String,
    ) {
        repo.location(mode, listener = mMotionListener)
    }

    fun navController(
        mode: NavMode,
        dest: String = ""
    ) {
        repo.navigation(mode, destination = dest, listener = mNavigationListener)
    }

    fun mapController(
        mode: String,
    ) {
        repo.map(mode, listener = mMotionListener)
    }

    fun chargeController(
        mode: ChargeMode,
    ) {
        repo.charge(mode, listener = mNavigationListener)
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
            50 -> Log.d(TAG, "charge level: 50%")
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
            Log.i(TAG, "result: $result message:$message")
//                when (result) {
//                    Definition.RESULT_OK -> // 도착 완료
//                    Definition.RESULT_FAILURE -> // 도착 실패
//                }
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Log.i(TAG, "status: $status data:$data")
        }
    }

    // 탐색
    private val mNavigationListener: ActionListener = object : ActionListener() {
        override fun onResult(status: Int, response: String) {
            Log.i(TAG, "status: $status response:$response")
//                when (status) {
//                    Definition.RESULT_OK -> // 도착 완료
//                    Definition.RESULT_FAILURE -> // 도착 실패
//                }
        }

        override fun onError(code: Int, message: String, extraData: String) {
            Log.i(TAG, "code: $code message:$message")
        }

        override fun onStatusUpdate(status: Int, data: String, extraData: String) {
            Log.i(TAG, "status: $status data:$data")
        }
    }

    // 위치
    private val mLocationListener: CommandListener = object : CommandListener() {
        override fun onResult(result: Int, message: String, extraData: String) {
            Log.i(TAG, "result: $result message:$message")
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
            Log.i(TAG, "status: $status data:$data")
        }
    }

    // 상태
    private val mStatusListener: StatusListener = object : StatusListener() {
        override fun onStatusUpdate(type: String, data: String) {
            Log.i(TAG, "type: $type data:$data")
        }
    }

    companion object {
        private const val TAG = "RobotViewModel"
    }
}