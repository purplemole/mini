package com.clobot.mini

import android.graphics.ImageFormat
import androidx.camera.core.ImageProxy
import com.clobot.mini.util.QrCodeAnalyzer
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

/**
 * TODO QrCodeAnalyzer - 이미지를 건너 뛰고 디 코딩을 수행 하면 안 됨.
 * 예외 발생 여부 확인 테스트
 */
class QrCodeAnalyzerTest {

    @Test
    fun `analyze() should skip non-supported image format`() {
        // mock Image Proxy 지정
        val mockImage = mockk<ImageProxy>()
        every { mockImage.format } returns ImageFormat.JPEG

        // mock Image 분석 후
        val analyzer = QrCodeAnalyzer {}
        analyzer.analyze(mockImage)
    }

}