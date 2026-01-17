package com.fr0g.moventure.movie.data.remote.api

import com.fr0g.moventure.BuildConfig
import com.fr0g.moventure.movie.data.remote.model.MovieDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie")
    suspend fun fetchDiscoverMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ): MovieDTO

    @GET("trending/movie/week")
    suspend fun fetchTrendingMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ): MovieDTO
}