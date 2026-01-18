package com.fr0g.moventure.detail.domain.models

import com.fr0g.moventure.detail.data.remote.model.Genre

data class Detail(
    val backdropPath: String,
    val genres: List<Genre>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val reviews: List<Review>,
    val runtime: String,
    val productionCountry: List<String>,
    val language: List<String>,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val cast: List<Cast>
)

data class Cast(
    val character: String,
    val id: Int,
    val name: String,
    val gender: Int,
    val profilePath: String?
) {
    private val nameParts = name.split(" ", limit = 2)
    val firstName = nameParts[0]
    val lastName = nameParts[1]
}

data class Review(
    val author: String,
    val content: String,
    val createdAt: String,
    val id: String,
    val rating: Double
)