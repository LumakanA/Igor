package ru.handh.school.igor.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import ru.handh.school.igor.ui.screen.about.AboutScreen
import ru.handh.school.igor.ui.screen.homepage.HomepageScreen
import ru.handh.school.igor.ui.screen.homepageDetails.HomepageDetailsScreen
import ru.handh.school.igor.ui.screen.profile.ProfileScreen
import ru.handh.school.igor.ui.screen.signin.SignInScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startScreen: String
) {

    NavHost(navController = navController, startDestination = startScreen) {
        composable(Screen.SignIn.route) {
            SignInScreen(
                vm = koinViewModel(), navController = navController
            )
        }
        composable(Screen.Homepage.route) {
            HomepageScreen(
                vm = koinViewModel(), navController = navController
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(vm = koinViewModel(), navController = navController)
        }
        composable(Screen.About.route) {
            AboutScreen(vm = koinViewModel(), navController = navController)
        }
        composable(Screen.ProjectDetails.route) {
            HomepageDetailsScreen(vm = koinViewModel(), navController = navController)
        }
    }
}

