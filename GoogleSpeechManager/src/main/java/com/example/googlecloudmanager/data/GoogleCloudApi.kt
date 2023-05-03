package com.example.googlecloudmanager.data

import java.io.File

interface GoogleCloudApi {
    fun textToAudio(text: String, file: File)
}