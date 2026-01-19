package com.fr0g.moventure.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fr0g.moventure.ui.detail.DetailScreen
import com.fr0g.moventure.ui.home.HomeScreen
import com.fr0g.moventure.ui.search.SearchScreen
import com.fr0g.moventure.ui.watchlist.WatchlistScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onOpenDrawer: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen().route,
        modifier = modifier.fillMaxSize(),
    ) {
        composable(
            route = Route.HomeScreen().route,
            enterTransition = { fadeIn() + scaleIn() },
            exitTransition = { fadeOut() + shrinkOut() }
        ) {
            HomeScreen(
                onMovieClick = {
                    navController.navigate(
                        Route.DetailScreen().getRouteWithArgs(id = it)
                    ) {
                        launchSingleTop = true
                    }
                },
                onMenuClick = onOpenDrawer,
                onSearchClick = {
                    navController.navigate(Route.SearchScreen().route)
                }
            )

        }

        composable(
            route = Route.WatchlistScreen().route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            WatchlistScreen(
                onNavigateUp = { navController.navigateUp() },
                onMovieClick = { id ->
                    navController.navigate(Route.DetailScreen().getRouteWithArgs(id))
                }
            )
        }

        composable(Route.SearchScreen().route) {
            SearchScreen(
                onNavigateUp = { navController.navigateUp() },
                onMovieClick = { id ->
                    navController.navigate(Route.DetailScreen().getRouteWithArgs(id))
                }
            )
        }

        composable(
            route = Route.DetailScreen().routeWithArgs,
            arguments = listOf(navArgument(name = "id") { type = NavType.IntType })
        ) {
            DetailScreen(
                onHomeClick = {
                    navController.navigate(Route.HomeScreen().route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onMovieClick = {
                    navController.navigate(
                        Route.DetailScreen().getRouteWithArgs(id = it)
                    )
                },
            )
        }
    }
}