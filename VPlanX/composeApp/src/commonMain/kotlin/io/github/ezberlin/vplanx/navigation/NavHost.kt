package io.github.ezberlin.vplanx.navigation

import androidx.compose.runtime.Composable
import io.github.ezberlin.vplanx.ui.FeedScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Feed) {
        composable<Feed> { FeedScreen() }
    }
}