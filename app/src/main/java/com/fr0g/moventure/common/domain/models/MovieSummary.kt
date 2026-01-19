package com.fr0g.moventure.common.domain.models

data class MovieSummary(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val genreIds: List<String> = emptyList(),
    val backdropPath: String? = null,
    val overview: String = "",
    val releaseDate: String = "",
    val voteCount: Int = 0
)
