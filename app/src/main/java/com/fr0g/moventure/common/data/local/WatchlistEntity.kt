package com.fr0g.moventure.common.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fr0g.moventure.common.domain.models.MovieSummary

@Entity(tableName = "watchlist")
data class WatchlistEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
    val addedAt: Long = System.currentTimeMillis()
)

// Map domain Movie to Entity
fun MovieSummary.toEntity() = WatchlistEntity(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage
)

// Convert Entity back to UI domain Model
fun WatchlistEntity.toDomain(): MovieSummary {
    return MovieSummary(
        id = id,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage,
        backdropPath = null,
        overview = "",
        releaseDate = "",
        voteCount = 0
    )
}