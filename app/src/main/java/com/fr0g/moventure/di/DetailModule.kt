package com.fr0g.moventure.di

import com.fr0g.moventure.common.data.mappers.ApiMapper
import com.fr0g.moventure.common.data.mappers.DetailApiMapper
import com.fr0g.moventure.common.data.implementation.DetailRepositoryImpl
import com.fr0g.moventure.common.data.remote.api.DetailApiService
import com.fr0g.moventure.common.data.remote.model.DetailDTO
import com.fr0g.moventure.common.domain.models.Detail
import com.fr0g.moventure.common.domain.repository.DetailRepository
import com.fr0g.moventure.common.data.remote.model.MovieDTO
import com.fr0g.moventure.common.domain.models.MovieSummary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailModule {

    @Provides
    @Singleton
    fun provideDetailRepository(
        detailApiService: DetailApiService,
        mapper: ApiMapper<Detail, DetailDTO>,
        movieMapper: ApiMapper<List<MovieSummary>, MovieDTO>
    ): DetailRepository = DetailRepositoryImpl(
        detailApiService, mapper, movieMapper
    )

    @Provides
    @Singleton
    fun provideDetailMapper(): ApiMapper<Detail, DetailDTO> = DetailApiMapper()

    @Provides
    @Singleton
    fun provideDetailApiService(retrofit: Retrofit): DetailApiService {
        return retrofit.create(DetailApiService::class.java)
    }
}