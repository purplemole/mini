package com.clobot.mini.data.robot

import com.ainirobot.coreservice.client.Definition.LOST_MAX_DISTANCE

data class DefaultValue (
    val reqId: Int = 0,
    val speed: Float = 0.2f,
    val distance: Float = 1f,
    val avoid: Boolean = true,
    val angle: Float = 90f
)