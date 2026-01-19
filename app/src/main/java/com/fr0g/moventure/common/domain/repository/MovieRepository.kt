package com.fr0g.moventure.common.domain.repository

import com.fr0g.moventure.common.domain.models.MovieSummary
import com.fr0g.moventure.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovie(): Flow<Response<List<MovieSummary>>>
    fun fetchTrendingMovie(): Flow<Response<List<MovieSummary>>>
    fun searchMovies(query: String): Flow<Response<List<MovieSummary>>>
}