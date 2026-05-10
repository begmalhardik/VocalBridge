package com.example.vocalbridge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocalbridge.ui.screen.PracticeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "practice"
    ) {
//        composable("onboarding") {
//            // OnboardingScreen(navController)
//        }
//
//        composable("dashboard") {
//            // DashboardScreen(navController)
//        }

        composable("practice") {
            PracticeScreen()
        }

//        composable("progress") {
//            // ProgressScreen()
//        }
    }
}