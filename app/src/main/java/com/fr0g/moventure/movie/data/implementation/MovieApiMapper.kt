package com.fr0g.moventure.movie.data.implementation

import com.fr0g.moventure.common.data.ApiMapper
import com.fr0g.moventure.movie.data.remote.model.MovieDTO
import com.fr0g.moventure.movie.domain.models.Movie
import com.fr0g.moventure.utils.GenreConstants

class MovieApiMapper: ApiMapper<List<Movie>, MovieDTO>{
    override fun mapToDomain(apiDTO: MovieDTO): List<Movie> {
        return apiDTO.results?.map { result ->
            Movie(
                backdropPath = formatEmptyValue(result?.backdropPath),
                genreIds = formatGenre(result?.genreIds),
                id = result?.id ?: 0,
                originalLanguage = formatEmptyValue(result?.originalLanguage),
                originalTitle = formatEmptyValue(result?.originalTitle),
                overview = formatEmptyValue(result?.overview),
                popularity = result?.popularity ?: 0.0,
                posterPath = formatEmptyValue(result?.posterPath),
                releaseDate = formatEmptyValue(result?.releaseDate),
                title = formatEmptyValue(result?.title),
                voteAverage = result?.voteAverage ?: 0.0,
                voteCount = result?.voteCount ?: 0,
                video = result?.video ?: false,
                adult = result?.adult ?: false
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