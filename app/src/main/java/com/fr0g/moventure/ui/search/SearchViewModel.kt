package com.fr0g.moventure.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fr0g.moventure.common.domain.models.MovieSummary
import com.fr0g.moventure.common.domain.repository.MovieRepository
import com.fr0g.moventure.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    // Search State
    sealed interface SearchUiState {
        data object Empty : SearchUiState
        data object Loading : SearchUiState
        data class Success(val movies: List<MovieSummary>) : SearchUiState
        data class Error(val message: String) : SearchUiState
    }

    var uiState: SearchUiState by mutableStateOf(SearchUiState.Empty)
        private set

    // Search job, for when the user types too fast
    private var searchJob: Job? = null

    fun onQueryChange(newQuery: String) {
        searchQuery = newQuery

        // Cancel previous search if user keeps typing
        searchJob?.cancel()

        if (newQuery.isBlank()) {
            uiState = SearchUiState.Empty
            return
        }

        searchJob = viewModelScope.launch {
            delay(500) // Debounce

            repository.searchMovies(newQuery).collect { response ->
                when (response) {
                    is Response.Loading -> {
                        uiState = SearchUiState.Loading
                    }

                    is Response.Success -> {
                        val movies = response.data
                        uiState = if (movies.isNullOrEmpty()) {
                            SearchUiState.Error("No movies found")
                        } else {
                            SearchUiState.Success(movies)
                        }
                    }

                    is Response.Error -> {
                        uiState = SearchUiState.Error(response.error?.message ?: "Unknown error")
                    }
                }
            }
        }
    }
}