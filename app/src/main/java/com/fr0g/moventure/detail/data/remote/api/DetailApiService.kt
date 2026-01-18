package com.fr0g.moventure.detail.data.remote.api

import com.fr0g.moventure.BuildConfig
import com.fr0g.moventure.detail.data.remote.model.DetailDTO
import com.fr0g.moventure.home.data.remote.model.MovieDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailApiService {

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("append_to_response") appendToResponse: String = "credits,reviews"
    ): DetailDTO

    @GET("movie/{movie_id}/similar")
    suspend fun fetchMovieSimilar(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey
    ): MovieDTO
}