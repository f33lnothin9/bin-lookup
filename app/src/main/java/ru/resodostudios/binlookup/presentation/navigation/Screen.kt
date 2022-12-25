package ru.resodostudios.binlookup.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object History: Screen(route = "history")
}
