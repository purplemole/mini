package com.clobot.mini.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clobot.mini.data.robot.*
import com.clobot.mini.repo.robot.AiniRobotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RobotViewModel @Inject constructor(
    private val repo: AiniRobotRepository,
//    private val ttsRepo: AiniTtsRepository
) : ViewModel() {
    private val tag = "RobotViewModel"

    private val _dockingState = MutableSharedFlow<Boolean>(replay = 1)
    val dockingState: SharedFlow<Boolean> = _dockingState

    private val robotVersion = repo.getVersion()

    private val _moveReason = mutableStateOf<MoveReason>(MoveReason.None)
    val moveReason = _moveReason.value//: State<MoveReason> = _moveReason

    private var reqId = 0

    init {
        viewModelScope.launch {
            repo.initialize().let {
                repo.dockingState.collectLatest {
                    _dockingState.emit(it)
                }
                repo.location("List")
            }
        }
    }

    fun changeMoveReason(newReason: MoveReason) {
        _moveReason.value = newReason
    }

    fun basicMotion(
        direction: MoveDirection,
    ) {
        repo.basicMotion(direction)
    }

    fun arcMotion(
        mode: ArcMode,
    ) {
        repo.arcMotion(mode)
    }

    fun headMotion(
        mode: String
    ) {
        repo.headMotion(mode, reqId++)
    }

    fun posController(
        mode: PosMode,
    ) {
        repo.position(mode)
    }

    fun locController(
        mode: String,
    ) {
        repo.location(mode)
    }

    fun navController(
        mode: NavMode,
        dest: String = ""
    ) {
        repo.navigation(mode, destination = dest)
    }

    fun mapController(
        mode: String,
    ) {
        repo.map(mode)
    }

    fun chargeController(
        mode: ChargeMode,
    ) {
        repo.charge(mode)
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
            50 -> Log.d(tag, "charge level: 50%")
            100 -> chargeController(ChargeMode.StopLeave)
        }
    }

    fun getPlaceList(): MutableList<String> {
        return repo.placeList
    }
}