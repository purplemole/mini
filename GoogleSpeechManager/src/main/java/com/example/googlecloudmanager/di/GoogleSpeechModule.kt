package com.example.googlecloudmanager.di

import com.example.googlecloudmanager.data.GoogleCloudApi
import com.example.googlecloudmanager.data.GoogleCloudApiImpl
import com.example.googlecloudmanager.domain.GoogleCloudRepository
import com.example.googlecloudmanager.domain.IGoogleCloudRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface GoogleSpeechModule {
    @Singleton
    @Binds
    fun bindsGoogleCloudApi(googleCloudApiImpl: GoogleCloudApiImpl):GoogleCloudApi

    @Singleton
    @Binds
    fun bindsGoogleCloudRepository(googleCloudRepository: GoogleCloudRepository): IGoogleCloudRepository
}