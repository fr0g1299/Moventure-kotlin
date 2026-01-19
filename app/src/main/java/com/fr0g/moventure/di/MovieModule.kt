package com.fr0g.moventure.di

import com.fr0g.moventure.common.data.mappers.ApiMapper
import com.fr0g.moventure.common.data.mappers.MovieApiMapper
import com.fr0g.moventure.common.data.remote.api.MovieApiService
import com.fr0g.moventure.common.data.remote.model.MovieDTO
import com.fr0g.moventure.common.domain.models.MovieSummary
import com.fr0g.moventure.common.domain.repository.MovieRepository
import com.fr0g.moventure.common.data.implementation.MovieRepositoryImpl as MRI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        mapper: ApiMapper<List<MovieSummary>, MovieDTO>
    ): MovieRepository = MRI(
        movieApiService, mapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<List<MovieSummary>, MovieDTO> = MovieApiMapper()

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}