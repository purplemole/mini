package com.clobot.mini.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.clobot.mini.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() :
    ViewModel() {
    //private lateinit var navController: NavHostController
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
        navController.navigate(NavRoute.MAIN.routeName) {
            popUpTo(NavRoute.MAIN.routeName)
        }
    }
}