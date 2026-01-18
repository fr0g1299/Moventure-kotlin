package com.fr0g.moventure.detail.data.implementation

import com.fr0g.moventure.common.data.ApiMapper
import com.fr0g.moventure.detail.data.remote.model.DetailDTO
import com.fr0g.moventure.detail.data.remote.model.Genre
import com.fr0g.moventure.detail.domain.models.Cast
import com.fr0g.moventure.detail.domain.models.Detail
import com.fr0g.moventure.detail.domain.models.Review

class DetailApiMapper : ApiMapper<Detail, DetailDTO> {
    override fun mapToDomain(apiDTO: DetailDTO): Detail {
        return Detail(
            backdropPath = formatEmptyValue(apiDTO.backdropPath),
            //genreIds = apiDTO.genres?.map { formatEmptyValue(it?.name) } ?: emptyList(),
            genres = apiDTO.genres?.map { Genre(id = it?.id, name = it?.name) } ?: emptyList(),
            id = apiDTO.id ?: 0,
            originalLanguage = formatEmptyValue(apiDTO.originalLanguage, "language"),
            originalTitle = formatEmptyValue(apiDTO.originalTitle, "title"),
            overview = formatEmptyValue(apiDTO.overview, "overview"),
            popularity = apiDTO.popularity ?: 0.0,
            posterPath = formatEmptyValue(apiDTO.posterPath),
            releaseDate = formatEmptyValue(apiDTO.releaseDate, "date"),
            title = formatEmptyValue(apiDTO.title, "title"),
            voteAverage = apiDTO.voteAverage ?: 0.0,
            voteCount = apiDTO.voteCount ?: 0,
            video = apiDTO.video ?: false,
            cast = apiDTO.credits?.cast?.map {
                Cast(
                    id = it?.id ?: 0,
                    name = formatEmptyValue(it?.name),
                    character = formatEmptyValue(it?.character),
                    gender = it?.gender ?: -1,
                    profilePath = it?.profilePath
                )
            } ?: emptyList(),
            language = apiDTO.spokenLanguages?.map { formatEmptyValue(it?.englishName) }
                ?: emptyList(),
            productionCountry = apiDTO.productionCountries?.map { formatEmptyValue(it?.name) }
                ?: emptyList(),
            reviews = apiDTO.reviews?.results?.map {
                Review(
                    author = formatEmptyValue(it?.author),
                    content = formatEmptyValue(it?.content),
                    createdAt = formatEmptyValue(it?.createdAt),
                    id = formatEmptyValue(it?.id),
                    rating = it?.authorDetails?.rating ?: 0.0
                )
            } ?: emptyList(),
            runtime = convertMinutesToHours(apiDTO.runtime ?: 0)
        )
    }

    private fun convertMinutesToHours(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return "${hours}h:${remainingMinutes}m"
    }

    private fun formatEmptyValue(value: String?, default: String = ""): String {
        if (value.isNullOrEmpty()) return "Unknown $default"
        return value
    }
}
