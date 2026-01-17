package com.fr0g.moventure.movie.domain.repository

import com.fr0g.moventure.movie.domain.models.Movie
import com.fr0g.moventure.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovie():Flow<Response<List<Movie>>>
    fun fetchTrendingMovie():Flow<Response<List<Movie>>>
}