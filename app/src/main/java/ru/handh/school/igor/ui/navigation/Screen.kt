package ru.handh.school.igor.ui.navigation

sealed class Screen(val route: String) {
    data object SignIn : Screen("signIn")
    data object Homepage : Screen("homepage")
    data object Profile : Screen("profile")
    data object About : Screen("about")
    data object ProjectDetails : Screen("projectDetails")
}


