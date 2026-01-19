package com.fr0g.moventure.navigation

sealed class Route {
    data class HomeScreen(val route: String = "homeScreen") : Route()

    data class WatchlistScreen(val route: String = "watchlistScreen") : Route()

    data class SearchScreen(val route: String = "searchScreen") : Route()

    data class DetailScreen(
        val route: String = "DetailScreen",
        val routeWithArgs: String = "$route/{id}",
    ) : Route() {
        fun getRouteWithArgs(id: Int): String {
            return "$route/$id"
        }
    }
}