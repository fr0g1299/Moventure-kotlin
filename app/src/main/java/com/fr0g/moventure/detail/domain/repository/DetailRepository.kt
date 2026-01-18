package com.fr0g.moventure.detail.domain.repository

import com.fr0g.moventure.detail.domain.models.Detail
import com.fr0g.moventure.home.domain.models.Movie
import com.fr0g.moventure.utils.Response
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun fetchMovieDetail(movieId: Int):Flow<Response<Detail>>
    fun fetchMovieSimilar(movieId: Int):Flow<Response<List<Movie>>>
}