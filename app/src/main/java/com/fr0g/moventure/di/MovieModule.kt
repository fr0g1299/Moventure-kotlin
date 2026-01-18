package com.fr0g.moventure.di

import com.fr0g.moventure.common.data.ApiMapper
import com.fr0g.moventure.home.data.implementation.MovieApiMapper
import com.fr0g.moventure.home.data.remote.api.MovieApiService
import com.fr0g.moventure.home.data.remote.model.MovieDTO
import com.fr0g.moventure.home.domain.models.Movie
import com.fr0g.moventure.home.domain.repository.MovieRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.fr0g.moventure.home.data.implementation.MovieRepositoryImpl as MRI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        mapper:ApiMapper<List<Movie>, MovieDTO>
    ): MovieRepository = MRI(
        movieApiService, mapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<List<Movie>, MovieDTO> = MovieApiMapper()

    @Provides
    @Singleton
    fun provideMovieApiService():MovieApiService{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieApiService::class.java)
    }
}