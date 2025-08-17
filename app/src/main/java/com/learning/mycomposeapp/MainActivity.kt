package com.learning.mycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.learning.mycomposeapp.ui.screen.QuoteAuthors
import com.learning.mycomposeapp.ui.screen.QuoteListByAuthor
import com.learning.mycomposeapp.ui.theme.MyComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyComposeAppTheme {
                LaunchApp()
            }
        }
    }

    @Composable
    fun LaunchApp() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "main_screen") {
            composable(route = "main_screen") {
                QuoteAuthors { authorName ->
                    navController.navigate("quote_list_screen/${authorName}")
                }
            }
            composable(
                route = "quote_list_screen/{author_name}",
                arguments = listOf(navArgument("author_name") { type = NavType.StringType })
            ) {
                // Getting argument data here. We can get it directly in viewmodel. See in ViewModel
                val authorName = it.arguments?.getString("author_name")
                QuoteListByAuthor {
                    navController.navigate("quote_list_screen")
                }
            }
        }
    }
}
