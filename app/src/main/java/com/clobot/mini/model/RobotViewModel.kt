package com.clobot.mini.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clobot.mini.repo.RobotRepository
import com.clobot.mini.data.robot.MoveReason
import com.clobot.mini.repo.robot.AiniRobotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RobotViewModel @Inject constructor(
    private val repo: AiniRobotRepository
) : ViewModel() {
    private val tag = "RobotViewModel"

    private val _dockingState = MutableSharedFlow<Boolean>(replay = 1)
    val dockingState: SharedFlow<Boolean> = _dockingState

    private val robotVersion = repo.getVersion()

    private val _moveReason = mutableStateOf<MoveReason>(MoveReason.None)
    val moveReason = _moveReason.value//: State<MoveReason> = _moveReason

    init {
        viewModelScope.launch {
            repo.initialize()
//            repo.checkDockingStation()
            repo.dockingState.collectLatest {
                _dockingState.emit(it)
            }
        }
    }

    fun changeMoveReason(newReason: MoveReason) {
        _moveReason.value = newReason
    }

    fun moveAround() {

    }
}