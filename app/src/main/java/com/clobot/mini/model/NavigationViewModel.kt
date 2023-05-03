package com.clobot.mini.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
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
        //Log.i("navCheck", "navTo ${route.routeName} (${route.description})")
        navController.navigate(route.routeName)
    }

    fun goBack() {
        val curNode = navController.backQueue.last().destination.route
        if (curNode != "main" || getCurPageInfo() != "main")
            navController.navigateUp()
    }

    fun goMain() {
        navController.navigate(NavRoute.Main.routeName) {
            popUpTo(NavRoute.Main.routeName)
        }
    }

    fun popToPos(pos: Int) {
        navController.popBackStack(navController.backQueue[pos].destination.id, false)
    }

    fun popTo(destination: String){
        navController.popBackStack(destination, true)
    }

    fun getCurPageInfo(): String? {
        return navController.currentDestination?.route
    }

    fun leftQueuePop(){
        navController.backQueue.removeFirst()
    }
}