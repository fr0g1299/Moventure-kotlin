package com.fr0g.moventure.home.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    // TODO: Remove unnecessary fields later
    val genreIds: List<String>,
    val adult: Boolean = false,
    val backdropPath: String? = null,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val releaseDate: String = "",
    val video: Boolean = false,
    val voteCount: Int = 0
)
