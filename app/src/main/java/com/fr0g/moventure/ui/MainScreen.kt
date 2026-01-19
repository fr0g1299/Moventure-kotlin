package com.fr0g.moventure.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.fr0g.moventure.ui.components.AppNavigationDrawer
import com.fr0g.moventure.ui.navigation.NavigationGraph
import com.fr0g.moventure.ui.navigation.Route
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Helper to close drawer and navigate
    fun navigateFromDrawer(route: String) {
        scope.launch {
            drawerState.close()
            navController.navigate(route) {
                // Pop up to Home to avoid stack pile-up
                popUpTo(Route.HomeScreen().route) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppNavigationDrawer(
                onNavigateToHome = { navigateFromDrawer(Route.HomeScreen().route) },
                onNavigateToWatchlist = { navigateFromDrawer(Route.WatchlistScreen().route) },
            )
        },
        gesturesEnabled = true
    ) {
        Scaffold { innerPadding ->
            NavigationGraph(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                onOpenDrawer = {
                    scope.launch { drawerState.open() }
                }
            )
        }
    }
}