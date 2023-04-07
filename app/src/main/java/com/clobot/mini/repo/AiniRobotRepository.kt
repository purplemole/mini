package com.clobot.mini.repo

import com.ainirobot.coreservice.client.RobotApi
import javax.inject.Inject


class AiniRobotRepository @Inject constructor() : RobotRepository {
    override fun getVersion(): String {
        return RobotApi.getInstance().version
    }
}