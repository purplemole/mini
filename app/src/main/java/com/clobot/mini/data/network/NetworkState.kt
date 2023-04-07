package com.clobot.mini.data.network

/**
 * None 최초 상태
 * Connected 연결 상태
 * NotConnected 비연결 상태
 */
sealed class NetworkState {
    object None: NetworkState()
    object Connected: NetworkState()
    object NotConnected: NetworkState()
}
