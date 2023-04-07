package com.clobot.mini.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clobot.mini.repo.RobotRepository
import com.clobot.mini.util.robot.DockingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MoveReason {
    DOCENT, SCHEDULE, GUIDE, HOME, NONE, DOCKING
}

@HiltViewModel
class RobotViewModel @Inject constructor(
    private val repo: RobotRepository
) : ViewModel() {
    companion object {
        private const val TAG = "RobotViewModel"
        private var moveReason = MutableLiveData(MoveReason.NONE)
    }
    private val _dockingState = MutableSharedFlow<DockingState>(replay = 1)
    val dockingState: SharedFlow<DockingState> = _dockingState

    val robotVersion: String = repo.getVersion()

    init {
        viewModelScope.launch {
            repo.initialize()
//            repo.checkDockingStation()
            repo.dockingState.collectLatest {
                _dockingState.emit(it)
            }
        }
    }
}