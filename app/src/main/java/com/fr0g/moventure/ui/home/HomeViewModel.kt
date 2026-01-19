package com.fr0g.moventure.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fr0g.moventure.common.domain.repository.WatchlistRepository
import com.fr0g.moventure.common.domain.models.MovieSummary
import com.fr0g.moventure.common.domain.repository.MovieRepository
import com.fr0g.moventure.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val watchlistRepo: WatchlistRepository
):ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    val bookmarkedIds: StateFlow<Set<Int>> = watchlistRepo.watchlistItems
        .map { list -> list.map { it.id }.toSet() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    fun toggleBookmark(movie: MovieSummary) {
        viewModelScope.launch {
            val isBookmarked = bookmarkedIds.value.contains(movie.id)
            watchlistRepo.toggleWatchlist(movie, isBookmarked)
        }
    }

    init {
        fetchDiscoverMovie()
    }
    init {
        fetchTrendingMovie()
    }

    private fun fetchDiscoverMovie() = viewModelScope.launch {
        repository.fetchDiscoverMovie().collectAndHandle(
            onError = {error ->
                _homeState.update {
                    it.copy(isLoading = false, error = error?.message)
                }
            },
            onLoading = {
                _homeState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        ) { movie ->
            _homeState.update {
                it.copy(isLoading = false, error = null, discoverMovies = movie)
            }
        }
    }

    private fun fetchTrendingMovie() = viewModelScope.launch {
        repository.fetchTrendingMovie().collectAndHandle(
            onError = {error ->
                _homeState.update {
                    it.copy(isLoading = false, error = error?.message)
                }
            },
            onLoading = {
                _homeState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        ) { movie ->
            _homeState.update {
                it.copy(isLoading = false, error = null, trendingMovies = movie)
            }
        }
    }
}

data class HomeState(
    val discoverMovies: List<MovieSummary> = emptyList(),
    val trendingMovies: List<MovieSummary> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)