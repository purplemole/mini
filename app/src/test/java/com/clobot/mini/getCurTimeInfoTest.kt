package com.clobot.mini

import com.clobot.mini.util.DateFormat
import com.clobot.mini.util.getCurTimeInfo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetCurTimeInfoTest {
    private var currentTime: Long = 0

    @Before
    fun setUp() {
        currentTime = 1683013049511L
    }
    @Test
    fun YMDEformatDate_isCorrect() {
        val result = getCurTimeInfo(DateFormat.YMDE, currentTime)
        Assert.assertEquals("23-05-02-화", result)
    }

    @Test
    fun YMDformatDate_isCorrect() {
        val result = getCurTimeInfo(DateFormat.YMD, currentTime)
        Assert.assertEquals( "2023-05-02", result)
    }
    @Test
    fun HOUR12formatDate_isCorrect() {
        val result = getCurTimeInfo(DateFormat.HOUR12, currentTime)
        Assert.assertEquals( "04:37:29", result)
    }
    @Test
    fun HOUR24formatDate_isCorrect() {
        val result = getCurTimeInfo(DateFormat.HOUR24, currentTime)
        Assert.assertEquals( "16:37:29", result)
    }
}