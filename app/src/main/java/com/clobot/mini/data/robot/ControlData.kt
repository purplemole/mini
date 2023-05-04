package com.clobot.mini.data.robot

sealed class MoveReason {
    object None : MoveReason()
    object Guide : MoveReason()
    object Home : MoveReason()
    object Docking : MoveReason()
    object Patrol: MoveReason()
}

sealed class MoveDirection {
    object Forward: MoveDirection()
    object Backward: MoveDirection()
    object TurnLeft: MoveDirection()
    object TurnRight: MoveDirection()
    object Stop: MoveDirection()
    object AllStop: MoveDirection()
    object TurnToTarget: MoveDirection()
    object StopTurnToTarget: MoveDirection()
    object Arc: MoveDirection()
    object ArcObstacle: MoveDirection()
}

sealed class PosMode {
    object Pos: PosMode()
    object GoPos: PosMode()
    object StopPos: PosMode()
}

sealed class NavMode {
    object Start: NavMode()
    object Stop: NavMode()
    object ToPos: NavMode()
    object StopToPos: NavMode()
    object Lead: NavMode()
    object StopLead: NavMode()
    object PosNavi: NavMode()
    object StopPosNavi: NavMode()
    object NaviBack: NavMode()
    object StopNaviBack: NavMode()
    object SetConfig: NavMode()
    object Config: NavMode()
    object Status: NavMode()
}

sealed class Cruise {
    object Start: Cruise()
    object Stop: Cruise()
    object Status: Cruise()
    object Clear: Cruise()
}

sealed class ChargeMode {
    object GoCharge: ChargeMode()
    object Start: ChargeMode()
    object Stop: ChargeMode()
    object Auto: ChargeMode()
    object StopAuto: ChargeMode()
    object Leave: ChargeMode()
    object StopLeave: ChargeMode()
    object Exits: ChargeMode()
    object Status: ChargeMode()
    object Level: ChargeMode()
    object Door: ChargeMode()
    object RemainBattTime: ChargeMode()
    object RemainChargeTime: ChargeMode()
}

sealed class TtsMode {
    object Play: TtsMode()
    object Stop: TtsMode()
    object Query: TtsMode()
}