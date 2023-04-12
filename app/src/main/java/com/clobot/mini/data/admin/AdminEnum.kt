package com.clobot.mini.data.admin

class AdminEnum {
    enum class AudioState {
        UP, DOWN, MUTE
    }

    enum class TravelCycle(val cycle: Int) {
        ONE(1),
        THREE(3),
        FIVE(5),
        TEN(10)
    }
}
