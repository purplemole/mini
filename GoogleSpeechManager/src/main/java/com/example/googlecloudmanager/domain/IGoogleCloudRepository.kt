package com.example.googlecloudmanager.domain

import com.example.googlecloudmanager.common.Resource
import kotlinx.coroutines.flow.Flow

interface IGoogleCloudRepository {
    fun speak(text: String, isLocal: Boolean = false): Flow<Resource<Boolean>>
    fun stop()
}