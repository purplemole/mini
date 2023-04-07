package com.clobot.mini.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.clobot.mini.repo.RobotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class MoveReason {
    DOCENT, SCHEDULE, GUIDE, HOME, NONE, DOCKING
}

@HiltViewModel
class RobotViewModel @Inject constructor(
    private val robotRepository: RobotRepository
) : ViewModel() {
    companion object {
        private const val TAG = "RobotViewModel"
        private var moveReason = MutableLiveData(MoveReason.NONE)
    }

    private val robotVersion: String = robotRepository.getVersion()
}