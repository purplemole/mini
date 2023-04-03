package com.clobot.mini.model

import android.util.Log
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner.current
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.clobot.mini.view.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() :
    ViewModel() {
    private val _navController = mutableStateOf<NavHostController?>(null)
    private val navController: NavHostController get() = _navController.value!!

    fun setNavController(controller: NavHostController) {
        _navController.value = controller
    }

    fun navTo(route: NavRoute) {
        navController.navigate(route.routeName)
    }

    fun goBack() {
        navController.navigateUp()
    }

    fun goMain() {
        navController.navigate(NavRoute.Main.routeName) {
            popUpTo(NavRoute.Main.routeName)
        }
    }
}