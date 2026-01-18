package com.fr0g.moventure.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fr0g.moventure.detail.domain.models.Detail
import com.fr0g.moventure.detail.domain.repository.DetailRepository
import com.fr0g.moventure.home.domain.models.Movie
import com.fr0g.moventure.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    val id: Int = savedStateHandle.get<Int>("id") ?: -1

    init {
        fetchMovieDetail()
    }

    private fun fetchMovieDetail() = viewModelScope.launch {
        if (id == -1) {
            _detailState.update {
                it.copy(isLoading = false, error = "Movie not found")
            }
        } else {
            repository.fetchMovieDetail(id).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { detail ->
                _detailState.update {
                    it.copy(isLoading = false, error = null, detail = detail)
                }
            }
        }
    }

    fun fetchSimilarMovies() = viewModelScope.launch {
        if (id == -1) {
            _detailState.update {
                it.copy(isMovieLoading = false, error = "Movie not found")
            }
        } else {
            repository.fetchMovieSimilar(id).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isMovieLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isMovieLoading = true, error = null)
                    }
                }
            ) { movies ->
                _detailState.update {
                    it.copy(isMovieLoading = false, error = null, movies = movies)
                }
            }
        }
    }
}


data class DetailState(
    val detail: Detail? = null,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false,
    val isMovieLoading: Boolean = false
)