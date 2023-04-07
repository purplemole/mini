package com.clobot.mini.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clobot.mini.util.network.NetworkChecker
import com.clobot.mini.data.network.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkChecker: NetworkChecker // 종속성
) : ViewModel() {
    private val _networkState = MutableSharedFlow<NetworkState>(replay = 1)
    val networkState: SharedFlow<NetworkState> = _networkState

    init {
        viewModelScope.launch {
            networkChecker.networkState.collectLatest {
                _networkState.emit(it)
            }
        }
    }

    fun onRetry() {
        // Do something.
    }
}