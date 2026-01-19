package com.fr0g.moventure.ui.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fr0g.moventure.common.data.local.toDomain
import com.fr0g.moventure.common.domain.repository.WatchlistRepository
import com.fr0g.moventure.common.domain.models.MovieSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val repository: WatchlistRepository
) : ViewModel() {

    val watchlistState: StateFlow<List<MovieSummary>> = repository.watchlistItems
        .map { entities ->
            entities.map { it.toDomain() }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeFromWatchlist(movie: MovieSummary) {
        viewModelScope.launch {
            repository.toggleWatchlist(movie, isCurrentlyBookmarked = true)
        }
    }
}