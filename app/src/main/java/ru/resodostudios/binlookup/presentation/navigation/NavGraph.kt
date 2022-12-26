package ru.resodostudios.binlookup.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.resodostudios.binlookup.presentation.screens.history.HomeScreen
import ru.resodostudios.binlookup.presentation.screens.home.HistoryScreen

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.History.route) {
            HistoryScreen(navController = navController)
        }
    }
}