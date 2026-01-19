package com.fr0g.moventure.common.domain.repository

import com.fr0g.moventure.common.data.local.WatchlistDao
import com.fr0g.moventure.common.data.local.WatchlistEntity
import com.fr0g.moventure.common.data.local.toEntity
import com.fr0g.moventure.home.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WatchlistRepository @Inject constructor(private val dao: WatchlistDao) {

    val watchlistItems: Flow<List<WatchlistEntity>> = dao.getAll()

    fun isBookmarked(id: Int): Flow<Boolean> = dao.isBookmarked(id)

    suspend fun toggleWatchlist(movie: Movie, isCurrentlyBookmarked: Boolean) {
        if (isCurrentlyBookmarked) {
            dao.delete(movie.id)
        } else {
            dao.insert(movie.toEntity())
        }
    }
}