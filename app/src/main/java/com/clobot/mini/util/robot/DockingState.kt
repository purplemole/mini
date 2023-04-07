package com.clobot.mini.util.robot

sealed class DockingState {
    object None: DockingState()
    object Connected: DockingState()
    object NotConnected: DockingState()
}
