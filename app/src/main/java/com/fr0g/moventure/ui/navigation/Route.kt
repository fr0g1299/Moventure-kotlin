package com.fr0g.moventure.ui.navigation

sealed class Route {
    data class HomeScreen(val route: String = "homeScreen") : Route()

    data class WatchlistScreen(val route: String = "watchlistScreen") : Route()

    data class FilmScreen(
        val route: String = "FilmScreen",
        val routeWithArgs: String = "$route/{id}",
    ) : Route() {
        fun getRouteWithArgs(id: Int): String {
            return "$route/$id"
        }
    }
}