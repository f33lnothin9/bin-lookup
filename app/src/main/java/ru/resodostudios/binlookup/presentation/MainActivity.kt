package ru.resodostudios.binlookup.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.resodostudios.binlookup.presentation.navigation.NavGraph
import ru.resodostudios.binlookup.presentation.ui.theme.BINLookupTheme

@ExperimentalComposeUiApi
@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            BINLookupTheme {
                val navController = rememberNavController()

                NavGraph(navController = navController)
            }
        }
    }
}