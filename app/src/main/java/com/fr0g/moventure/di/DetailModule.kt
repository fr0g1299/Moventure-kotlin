package com.fr0g.moventure.di

import com.fr0g.moventure.common.data.ApiMapper
import com.fr0g.moventure.detail.data.implementation.DetailApiMapper
import com.fr0g.moventure.detail.data.implementation.DetailRepositoryImpl
import com.fr0g.moventure.detail.data.remote.api.DetailApiService
import com.fr0g.moventure.detail.data.remote.model.DetailDTO
import com.fr0g.moventure.detail.domain.models.Detail
import com.fr0g.moventure.detail.domain.repository.DetailRepository
import com.fr0g.moventure.home.data.remote.model.MovieDTO
import com.fr0g.moventure.home.domain.models.Movie
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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
object DetailModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideDetailRepository(
        detailApiService: DetailApiService,
        mapper: ApiMapper<Detail, DetailDTO>,
        movieMapper: ApiMapper<List<Movie>, MovieDTO>
    ): DetailRepository = DetailRepositoryImpl(
        detailApiService, mapper, movieMapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<Detail, DetailDTO> = DetailApiMapper()

    @Provides
    @Singleton
    fun provideDetailApiService():DetailApiService{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(DetailApiService::class.java)
    }
}