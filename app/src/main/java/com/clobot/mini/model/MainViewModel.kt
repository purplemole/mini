package com.clobot.mini.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clobot.mini.data.common.AlertState
import com.clobot.mini.util.network.NetworkChecker
import com.clobot.mini.data.network.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkChecker: NetworkChecker // 종속성
) : ViewModel() {
    private val _networkState = MutableSharedFlow<NetworkState>(replay = 1)
    val networkState: SharedFlow<NetworkState> = _networkState

    private val _alertState = MutableStateFlow(AlertState(visible = false, "", ""))
    val alertState get() = _alertState

    init {
        viewModelScope.launch {
            networkChecker.networkState.collectLatest {
                _networkState.emit(it)
                if (it === NetworkState.NotConnected) {
                   showNetworkAlert()
                }
            }
        }
    }

    // Global Alert Popup
    fun showNetworkAlert() {
        showAlertState(
            "네트워크 없음",
            "네트워크 연결이 끊어졌습니다. \n" +
                    "잠시 후 다시 이용하시는 건 어떠세요?"
        )
    }
    fun showAlertState(title: String, message: String) {
        _alertState.update {
            it.copy(title = title, message = message, visible = true)
        }
    }

    fun hideAlertState() {
        _alertState.update {
            it.copy(title = "", message = "", visible = false)
        }
    }

    fun onRetry() {
        // Do something.
    }
}