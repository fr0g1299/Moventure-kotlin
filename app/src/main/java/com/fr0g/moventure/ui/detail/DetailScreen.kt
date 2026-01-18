package com.fr0g.moventure.ui.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fr0g.moventure.ui.components.LoadingView
import com.fr0g.moventure.ui.detail.components.DetailBodyContent

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailViewMode: DetailViewModel = hiltViewModel(),
    onHomeClick: () -> Unit,
    onMovieClick: (Int) -> Unit,
) {
    val state by detailViewMode.detailState.collectAsStateWithLifecycle()
    Box(modifier = modifier.fillMaxWidth()) {
        AnimatedVisibility(state.error != null, modifier = Modifier.align(Alignment.TopCenter)) {
            Text(
                text = state.error ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )
        }

        AnimatedVisibility(visible = !state.isLoading && state.error == null) {
            Box(modifier = Modifier.fillMaxSize()) {
                state.detail?.let { detail ->
                    DetailBodyContent(
                        movieDetail = detail,
                        movies = state.movies,
                        isMovieLoading = state.isLoading,
                        fetchMovies = detailViewMode::fetchSimilarMovies,
                        onMovieClick = onMovieClick,
                        onHomeClick = onHomeClick
                    )
                }
            }
        }
    }
    LoadingView(isLoading = state.isLoading)
}