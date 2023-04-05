package com.clobot.mini.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * type1 = SimpleDateFormat("yyyy-MM-dd") // 년 월 일
 * type2 = SimpleDateFormat("yy-MM-dd-E") // 년(20XX) 월 일 요일
 * type3 = SimpleDateFormat("hh:mm:ss") // 시(1~12) 분 초
 * type4 = SimpleDateFormat("HH:mm:ss") // 시(0~23) 분 초
 */
@SuppressLint("SimpleDateFormat")
fun getCurTimeInfo(formatType: Int): String {
    val currentTime: Long = System.currentTimeMillis()

    val dataFormat1 = SimpleDateFormat("yyyy-MM-dd") // 년 월 일
    val dataFormat2 = SimpleDateFormat("yy-MM-dd-E") // 년(20XX) 월 일 요일
    val dataFormat3 = SimpleDateFormat("hh:mm:ss") // 시(1~12) 분 초
    val dataFormat4 = SimpleDateFormat("HH:mm:ss") // 시(0~23) 분 초

    val curT = when (formatType) {
        1 -> dataFormat1.format(currentTime)
        2 -> dataFormat2.format(currentTime)
        3 -> dataFormat3.format(currentTime)
        4 -> dataFormat4.format(currentTime)
        else -> dataFormat1.format(currentTime)
    }

    return curT
}