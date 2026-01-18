package com.fr0g.moventure.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fr0g.moventure.ui.components.LoadingView
import com.fr0g.moventure.ui.home.components.BodyContent
import com.fr0g.moventure.ui.home.components.TopContent
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (id: Int) -> Unit
) {
    var isAutoScrolling by remember {
        mutableStateOf(true)
    }
    val state by homeViewModel.homeState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { state.discoverMovies.size }
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    LaunchedEffect(key1 = pagerState.currentPage, key2 = isDragged) {
        if (isDragged) {
            isAutoScrolling = false
        } else {
            isAutoScrolling = true
            delay(5000)
            with(pagerState) {
                val target = if (currentPage < state.discoverMovies.size - 1) currentPage + 1 else 0
                //scrollToPage(target)
                animateScrollToPage(
                    page = target,
                    animationSpec = tween(durationMillis = 1200)
                )
            }


        }
    }
    Box(modifier = modifier) {
        AnimatedVisibility(visible = state.error != null) {
            Text(
                text = state.error ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )
        }
        AnimatedVisibility(visible = !state.isLoading && state.error == null) {
            BodyContent(
                modifier = Modifier.fillMaxSize(), // Maybe add statusBarsPadding if it breaks
                discoverMovies = state.discoverMovies,
                trendingMovies = state.trendingMovies,
                onMovieClick = onMovieClick,

                topContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            pageSize = PageSize.Fixed(200.dp),
                            snapPosition = SnapPosition.Center,
                            modifier = Modifier.height(400.dp)
                        ) { page ->
                            val pageOffset =
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                            val scale = lerp(
                                start = 0.85f,
                                stop = 1.00f,
                                fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                            )

                            Box(
                                modifier = Modifier
                                    .graphicsLayer {
                                        scaleX = scale
                                        scaleY = scale
                                        alpha = scale
                                    }
                            ) {
                                TopContent(
                                    modifier = Modifier.align(Alignment.Center),
                                    movie = state.discoverMovies[page],
                                    onMovieClick = { onMovieClick(it) }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
    LoadingView(isLoading = state.isLoading)
}