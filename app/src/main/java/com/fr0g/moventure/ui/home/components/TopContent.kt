package com.fr0g.moventure.ui.home.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.fr0g.moventure.home.domain.models.Movie
import com.fr0g.moventure.ui.home.defaultPadding

@Composable
fun TopContent(modifier: Modifier = Modifier, movie: Movie, onMovieClick: (id: Int) -> Unit) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
        .crossfade(true)
        .build()

    Column(
        modifier = modifier
            .width(200.dp)
            .clickable { onMovieClick(movie.id) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imgRequest,
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            onError = { it.result.throwable.printStackTrace() }
        )

        MovieDetail(
            rating = movie.voteAverage,
            title = movie.title,
            genre = movie.genreIds,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}

@Composable
fun MovieDetail(modifier: Modifier = Modifier, rating: Double, title: String, genre: List<String>) {
    Column(modifier = modifier.padding(defaultPadding)) {
        MovieCard {
            Row(
                modifier = Modifier.padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Rating",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(style = MaterialTheme.typography.bodySmall, text = "%.1f".format(rating))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .basicMarquee(),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        MovieCard {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                genre.take(3).forEachIndexed { index, genre ->
                    if (index > 0) {
                        VerticalDivider(
                            modifier = Modifier
                                .height(12.dp),
                            color = Color.White
                        )
                    }

                    Text(
                        text = genre,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f)
                            .basicMarquee(),
                        maxLines = 1,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
            }
        }
    }
}
