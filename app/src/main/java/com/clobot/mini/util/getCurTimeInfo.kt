package com.clobot.mini.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * @param formatType  포맷 타입
 * @param currentTime 시간 정수값
 */
@SuppressLint("SimpleDateFormat")
fun getCurTimeInfo(formatType: DateFormat, currentTime: Long): String {
    return formatType.format(currentTime)
}


/**
 * @sample YMD
 * @sample YMDE
 * @sample HOUR12
 * @sample HOUR24
 * */
enum class DateFormat(private val format: String) {
    YMD("yyyy-MM-dd"),
    YMDE("yy-MM-dd-E"),
    HOUR12("hh:mm:ss"),
    HOUR24("HH:mm:ss");

    fun format(time: Long): String = (SimpleDateFormat(this.format).format(time))
}