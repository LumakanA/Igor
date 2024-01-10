package ru.handh.school.igor.ui.navigation

import androidx.navigation.NavController

class NavigationGraph(private val navController: NavController) {

    fun navigateTo(screen: Screen) {
        navController.navigate(screen.route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}