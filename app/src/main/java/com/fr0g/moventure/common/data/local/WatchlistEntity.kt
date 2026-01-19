package com.fr0g.moventure.common.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fr0g.moventure.home.domain.models.Movie

@Entity(tableName = "watchlist")
data class WatchlistEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val addedAt: Long = System.currentTimeMillis()
)

// Map domain Movie to Entity
fun Movie.toEntity() = WatchlistEntity(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage
)

// Convert Entity back to UI domain Model
fun WatchlistEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage,
        genreIds = emptyList(),
        overview = "",
        releaseDate = "",
        backdropPath = null,
        adult = false,
        originalLanguage = "en",
        originalTitle = title,
        popularity = 0.0,
        video = false,
        voteCount = 0
    )
}