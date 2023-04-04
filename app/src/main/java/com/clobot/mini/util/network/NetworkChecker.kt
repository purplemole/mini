package com.clobot.mini.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkChecker constructor(
    private val context: Context
) {
    private val _networkState = MutableStateFlow<NetworkState>(NetworkState.None)
    val networkState: StateFlow<NetworkState> = _networkState

    private val validTransportTypes by lazy {
        listOf(
            NetworkCapabilities.TRANSPORT_WIFI,
            NetworkCapabilities.TRANSPORT_CELLULAR
        )
    }

    private val networkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)?.let { service ->
                    val connectivityManager = service as ConnectivityManager
                    connectivityManager.getNetworkCapabilities(network)?.let { networkCapabilities ->
                        if (validTransportTypes.any { networkCapabilities.hasTransport(it) }) {
                            _networkState.value = NetworkState.Connected
                        } else {
                            _networkState.value = NetworkState.NotConnected
                        }
                    } ?: run {
                        _networkState.value = NetworkState.NotConnected
                    }
                }
                Log.d("NetworkChecker", "Network $network is now available")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _networkState.value = NetworkState.NotConnected
                Log.d("NetworkChecker", "Network $network is no longer available")
            }
        }
    }

    private val connectivityManager: ConnectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    init {
        connectivityManager.activeNetwork?.let { network ->
            connectivityManager.getNetworkCapabilities(network)?.let { networkCapabilities ->
                if (validTransportTypes.any { networkCapabilities.hasTransport(it) }) {
                    _networkState.value = NetworkState.Connected
                } else {
                    _networkState.value = NetworkState.NotConnected
                }
            } ?: run {
                _networkState.value = NetworkState.NotConnected
            }
        } ?: run {
            _networkState.value = NetworkState.NotConnected
        }

        val builder = NetworkRequest.Builder().apply {
            validTransportTypes.onEach { addTransportType(it) }
        }

        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    fun onAvailable(network: Network?) {
        network?.let {
            Log.d("NetworkChecker", "Network $network is now available")
            networkCallback.onAvailable(it)
        } ?: run {
            Log.d("NetworkChecker", "Network is null")
        }
    }

    fun onLost(network: Network?) {
        if (network != null) {
            Log.d("NetworkChecker", "Network $network is no longer available")
            networkCallback.onLost(network)
        }
    }
}