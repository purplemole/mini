package com.clobot.mini.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * @param formatType YMD - 년 월 일,
 * YMDE - 년(20XX) 월 일 요일,
 * HOUR12 - 시(1~12) 분 초,
 * HOUR24 - 시(0~23) 분 초
 * @param currentTime 시간 정수값
 */
@SuppressLint("SimpleDateFormat")
fun getCurTimeInfo(formatType: DateFormat, currentTime: Long): String {
    return formatType.format(currentTime)
}

enum class DateFormat(private val format: String) {
    YMD("yyyy-MM-dd"),
    YMDE("yy-MM-dd-E"),
    HOUR12("hh:mm:ss"),
    HOUR24("HH:mm:ss");

    fun format(time: Long):String = ( SimpleDateFormat(this.format).format(time) )
}