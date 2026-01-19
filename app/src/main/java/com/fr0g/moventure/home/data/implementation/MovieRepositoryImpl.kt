package com.fr0g.moventure.home.data.implementation

import com.fr0g.moventure.common.data.ApiMapper
import com.fr0g.moventure.home.data.remote.api.MovieApiService
import com.fr0g.moventure.home.data.remote.model.MovieDTO
import com.fr0g.moventure.home.domain.models.Movie
import com.fr0g.moventure.home.domain.repository.MovieRepository
import com.fr0g.moventure.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>, MovieDTO>
) : MovieRepository {

    override fun fetchDiscoverMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDTO = movieApiService.fetchDiscoverMovie()
        apiMapper.mapToDomain(movieDTO).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }

    override fun fetchTrendingMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDTO = movieApiService.fetchTrendingMovie()
        apiMapper.mapToDomain(movieDTO).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }

    override fun searchMovies(query: String): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDTO = movieApiService.searchMovies(query = query)
        apiMapper.mapToDomain(movieDTO).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }

}