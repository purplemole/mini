package com.clobot.mini.data.robot

sealed class DockingState {
    object None: DockingState()
    object Connected: DockingState()
    object NotConnected: DockingState()
}
