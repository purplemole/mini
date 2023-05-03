package com.clobot.mini

import android.content.Context
import com.clobot.mini.util.network.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Hilt 의 모듈 표시
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides // 객체 제공 함수 임을 표시 하는 방법
    @Singleton
    fun provideNetworkChecker(@ApplicationContext context: Context): NetworkChecker =
        NetworkChecker(context)
}