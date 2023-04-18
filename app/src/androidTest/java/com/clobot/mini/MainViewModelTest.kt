package com.clobot.mini

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.clobot.mini.model.MainViewModel
import com.clobot.mini.util.network.NetworkChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

// 참고 : https://developer.android.com/training/dependency-injection/hilt-testing?hl=ko
@HiltViewModel
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    // HiltAndroidRule : 구성 요소의 상태를 관리, 테스트에서 삽입을 실행하는데 사용
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    // 유형을 테스트에 삽입하기 위해 필드 삽입
    @Inject
    lateinit var networkChecker : NetworkChecker

    @Before
    fun setUp(){
        // Hilt에 @Inject 필드를 채우도록 알리기 위해 호출
        hiltRule.inject()
    }

    @Test
    fun viewModelTest(){

    }
}