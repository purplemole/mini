package com.example.mini.util

import android.os.SystemClock
import androidx.compose.runtime.Composable //Unit 전달 방법?
import com.example.mini.navigation.RouteAction

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
        }
    }
}
