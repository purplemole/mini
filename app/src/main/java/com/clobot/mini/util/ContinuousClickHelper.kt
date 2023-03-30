package com.clobot.mini.util

import android.os.SystemClock
import android.util.Log
import com.clobot.mini.navigation.NavRoute
import com.clobot.mini.navigation.RouteAction

/**
 * TODO touch 함수의 callback parameter 수정, routeAction 추가
 *
 * @property timesRequired 필요 클릭 횟수
 * @property timeTimeout 연속 클릭 제한 시간
 */
class ContinuousClickHelper(
    private val timesRequired: Int = 5,
    private val timeTimeout: Int = 3000
) {
    private var clickTime = 0
    private var lastClickTime: Long = 0

    fun touch(routeAction: RouteAction) {
        if (SystemClock.elapsedRealtime() - lastClickTime < timeTimeout) {
            clickTime++
        } else {
            clickTime = 1
        }

        lastClickTime = SystemClock.elapsedRealtime()

        if(clickTime == timesRequired){
                // routeAction 수행
            routeAction.navTo(NavRoute.ADMIN)
            Log.i("ContinuousClickHelper", "관리자 페이지 진입 가능")
        }
    }
}
