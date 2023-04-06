package com.clobot.mini.model

import android.os.RemoteException
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ainirobot.coreservice.client.Definition
import com.ainirobot.coreservice.client.RobotApi
import com.ainirobot.coreservice.client.StatusListener
import com.clobot.mini.repo.RobotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RobotViewModel @Inject constructor(
    private val robotRepository: RobotRepository
) : ViewModel() {
    private val robotState = RobotApi.getInstance().getRobotStatus(
        Definition.STATUS_EMERGENCY,
        object : StatusListener() {
            @Throws(RemoteException::class)
            override fun onStatusUpdate(type: String, data: String) {
                Log.d("onStatusUpdate", "Status:$data")
            }
        })
}