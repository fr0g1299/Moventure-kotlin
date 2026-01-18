package com.fr0g.moventure.detail.data.implementation

import com.fr0g.moventure.common.data.ApiMapper
import com.fr0g.moventure.detail.data.remote.api.DetailApiService
import com.fr0g.moventure.detail.data.remote.model.DetailDTO
import com.fr0g.moventure.detail.domain.models.Detail
import com.fr0g.moventure.detail.domain.repository.DetailRepository
import com.fr0g.moventure.home.data.remote.model.MovieDTO
import com.fr0g.moventure.home.domain.models.Movie
import com.fr0g.moventure.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DetailRepositoryImpl(
    private val detailApiService: DetailApiService,
    private val detailApiMapper: ApiMapper<Detail, DetailDTO>,
    private val movieApiMapper: ApiMapper<List<Movie>, MovieDTO>
) : DetailRepository {

    override fun fetchMovieDetail(movieId: Int): Flow<Response<Detail>> = flow {
        emit(Response.Loading())
        val detailDTO = detailApiService.fetchMovieDetail(movieId)
        detailApiMapper.mapToDomain(detailDTO).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun fetchMovieSimilar(movieId: Int): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val similarDTO = detailApiService.fetchMovieSimilar(movieId)
        movieApiMapper.mapToDomain(similarDTO).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

}