package com.fr0g.moventure.common.data.mappers

import com.fr0g.moventure.common.data.remote.model.MovieDTO
import com.fr0g.moventure.common.domain.models.MovieSummary
import com.fr0g.moventure.utils.GenreConstants

class MovieApiMapper: ApiMapper<List<MovieSummary>, MovieDTO> {
    override fun mapToDomain(apiDTO: MovieDTO): List<MovieSummary> {
        return apiDTO.results?.map { result ->
            MovieSummary(
                backdropPath = formatEmptyValue(result?.backdropPath),
                posterPath = formatEmptyValue(result?.posterPath),
                id = result?.id ?: 0,
                title = formatEmptyValue(result?.title),
                overview = formatEmptyValue(result?.overview),
                releaseDate = formatEmptyValue(result?.releaseDate),
                voteAverage = result?.voteAverage ?: 0.0,
                voteCount = result?.voteCount ?: 0,
                genreIds = formatGenre(result?.genreIds)
            )
        } ?: emptyList()
    }


    private fun formatEmptyValue(value: String?, default: String = ""): String {
        if (value.isNullOrEmpty()) return "Unknown $default"
        return value
    }

    private fun formatGenre(genreIds: List<Int?>?): List<String> {
        return genreIds?.map { GenreConstants.getGenreNameById(it ?: 0) } ?: emptyList()
    }
}