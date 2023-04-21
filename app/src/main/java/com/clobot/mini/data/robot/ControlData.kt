package com.clobot.mini.data.robot

sealed class MoveReason {
    object Docent : MoveReason()
    object Schedule : MoveReason()
    object Guide : MoveReason()
    object Home : MoveReason()
    object None : MoveReason()
    object Docking : MoveReason()
}

sealed class MoveDirection {
    object Forward: MoveDirection()
    object Backward: MoveDirection()
    object TurnLeft: MoveDirection()
    object TurnRight: MoveDirection()
    object Stop: MoveDirection()
}

sealed class ArcMode {
    object None: ArcMode()
    object Obstacle: ArcMode()
}

sealed class PosMode {
    object Set: PosMode()
    object Get: PosMode()
    object Pos: PosMode()
    object Remove: PosMode()
    object Init: PosMode()
    object IsInit: PosMode()
    object Switch: PosMode()
    object IsPos: PosMode()
    object MapName: PosMode()
    object List: PosMode()
}

sealed class NavMode {
    object Start: NavMode()
    object Stop: NavMode()
    object GoPos: NavMode()
    object StopPos: NavMode()
    object ToPos: NavMode()
    object Cruise: NavMode()
    object StopCruise: NavMode()
    object Lead: NavMode()
    object StopLead: NavMode()
}

sealed class ChargeMode {
    object Start: ChargeMode()
    object Stop: ChargeMode()
    object Leave: ChargeMode()
    object StopLeave: ChargeMode()
    object Standby: ChargeMode()
    object Wake: ChargeMode()
}

sealed class TtsMode {
    object Play: TtsMode()
    object Stop: TtsMode()
    object Query: TtsMode()
}