package com.clobot.mini.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlecloudmanager.data.Resource
import com.example.googlecloudmanager.domain.GoogleCloudRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TTSViewModel @Inject constructor(
    private val googleSpeechRepository: GoogleCloudRepository
) : ViewModel() {

    fun speak() = viewModelScope.launch {
        Timber.d("speak called")
        googleSpeechRepository.speak("안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요").stateIn(viewModelScope).collect {
            when(it) {
                is Resource.Error -> Timber.d("Error Occurd", it.message)
                else -> {}
            }
        }
    }

    fun localSpeak(filename: String) = viewModelScope.launch {
        Timber.d("localSpeak called")
        googleSpeechRepository.localSpeak(filename).stateIn(viewModelScope)
    }
}