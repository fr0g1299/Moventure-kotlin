package com.fr0g.moventure.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.fr0g.moventure.home.domain.models.Movie
import com.fr0g.moventure.ui.home.itemSpacing

@Composable
fun MovieCoverImage(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (Int) -> Unit
) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
        .crossfade(true)
        .build()

    Box(
        modifier = modifier.size(width = 150.dp, height = 250.dp)
            .padding(itemSpacing)
            .clickable { onMovieClick(movie.id) }
    ) {
        AsyncImage(
            model = imgRequest,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                //.aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(12.dp))
                .shadow(elevation = 4.dp),
            contentScale = ContentScale.Crop,
            onError = { it.result.throwable.printStackTrace() }
        )
        MovieCard(
            shapes = CircleShape,
            modifier = Modifier.align(Alignment.TopEnd)
                .padding(4.dp)
        ){
            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = null,
                modifier = Modifier
                    .padding(6.dp)
                    .size(20.dp)
            )
        }
    }
}