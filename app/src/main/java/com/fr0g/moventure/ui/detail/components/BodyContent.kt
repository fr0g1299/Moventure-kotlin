package com.fr0g.moventure.ui.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fr0g.moventure.common.domain.models.Detail
import com.fr0g.moventure.common.domain.models.MovieSummary
import com.fr0g.moventure.ui.components.MovieCard

@Composable
fun DetailBodyContent(
    movieDetail: Detail,
    movies: List<MovieSummary>,
    isMovieLoading: Boolean,
    fetchMovies: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onHomeClick: () -> Unit,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            DetailTopContent(
                movieDetail = movieDetail,
                onHomeClick = onHomeClick
            )
        }

        // Title & Overview Section
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                // Title
                Text(
                    text = movieDetail.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Genres Row
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(movieDetail.genres) { genre ->
                        GenreChip(text = genre.name ?: "")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Overview
                Text(
                    text = movieDetail.overview,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Bookmark Button
                ActionBar(isBookmarked = isBookmarked, onBookmarkClick = onBookmarkClick)
            }
        }

        // Cast Section
        item {
            SectionHeader(title = "Top Cast", onArrowClick = null)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(movieDetail.cast) { cast ->
                    ActorItem(
                        cast = cast
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        // Movie Info Grid
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoColumn(title = "Language", value = movieDetail.language.firstOrNull() ?: "-")
                InfoColumn(title = "Duration", value = movieDetail.runtime)
                InfoColumn(
                    title = "Country",
                    value = movieDetail.productionCountry.firstOrNull() ?: "-"
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }

        // Reviews Section
        item {
            SectionHeader(title = "Reviews", onArrowClick = null)
            ReviewList(reviews = movieDetail.reviews)
            Spacer(modifier = Modifier.height(32.dp))
        }

        // Similar Movies Section
        item {
            SimilarMovies(
                fetchMovies = fetchMovies,
                isMovieLoading = isMovieLoading,
                movies = movies,
                onMovieClick = onMovieClick
            )
        }
    }
}

@Composable
fun SimilarMovies(
    modifier: Modifier = Modifier,
    fetchMovies: () -> Unit,
    isMovieLoading: Boolean,
    movies: List<MovieSummary>,
    onMovieClick: (Int) -> Unit
) {
    LaunchedEffect(key1 = true) {
        fetchMovies()
    }
    Column(modifier) {
        Text(
            text = "More like this",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                AnimatedVisibility(visible = isMovieLoading) {
                    CircularProgressIndicator()
                }
            }
            items(movies) {
                MovieCard(
                    movie = it,
                    onMovieClick = onMovieClick,
                    showBookmark = false,
                    isBookmarked = false,
                    onBookmarkClick = {})
            }
        }
    }
}

@Composable
fun GenreChip(text: String) {
    Box(
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun InfoColumn(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit
) {
    val icon = if (isBookmarked) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder
    val contentDescription = if (isBookmarked) "Saved" else "Watchlist"

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onBookmarkClick() }
                .padding(8.dp)
                .size(80.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "BOOKMARK",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SectionHeader(title: String, onArrowClick: (() -> Unit)?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        if (onArrowClick != null) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "View All",
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onArrowClick() },
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}