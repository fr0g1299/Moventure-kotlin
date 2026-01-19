package com.fr0g.moventure.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fr0g.moventure.home.domain.models.Movie


@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    discoverMovies: List<Movie>,
    trendingMovies: List<Movie>,
    onMovieClick: (id: Int) -> Unit,
    topContent: @Composable () -> Unit,
    bookmarkedIds: Set<Int>,
    onBookmarkClick: (Movie) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        // Carousel
        item {
            topContent()
        }

        // Discover
        item {
            SectionHeader(
                title = "Discover Movies",
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 16.dp)
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(discoverMovies) { movie ->
                    MovieCard(movie = movie, onMovieClick = onMovieClick, isBookmarked = bookmarkedIds.contains(movie.id), onBookmarkClick = { onBookmarkClick(movie) })
                }
            }
        }

        // Trending
        item {
            SectionHeader(
                title = "Trending Now",
                modifier = Modifier.padding(start = 24.dp, top = 32.dp, bottom = 16.dp)
            )
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(trendingMovies) { movie ->
                    MovieCard(movie = movie, onMovieClick = onMovieClick, isBookmarked = bookmarkedIds.contains(movie.id), onBookmarkClick = { onBookmarkClick(movie) })
                }
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    )
}