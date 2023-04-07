package com.clobot.mini.data.robot

sealed class MoveReason {
    object Docent : MoveReason()
    object Schedule : MoveReason()
    object Guide : MoveReason()
    object Home : MoveReason()
    object None : MoveReason()
    object Docking : MoveReason()
}
