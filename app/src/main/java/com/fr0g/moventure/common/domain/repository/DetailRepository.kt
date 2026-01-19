package com.fr0g.moventure.common.domain.repository

import com.fr0g.moventure.common.domain.models.Detail
import com.fr0g.moventure.common.domain.models.MovieSummary
import com.fr0g.moventure.utils.Response
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun fetchMovieDetail(movieId: Int):Flow<Response<Detail>>
    fun fetchMovieSimilar(movieId: Int): Flow<Response<List<MovieSummary>>>
}