package com.example.googlecloudmanager.domain

import com.example.googlecloudmanager.data.Resource
import kotlinx.coroutines.flow.Flow

interface IGoogleCloudRepository {
    fun speak(text: String): Flow<Resource<Boolean>>
    fun localSpeak(filename: String): Flow<Resource<Boolean>>
    fun stop()
}