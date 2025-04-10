package com.comet.movieapp.data.repository

import com.comet.movieapp.common.Constants.API_KEY
import com.comet.movieapp.common.Constants.LANGUAGE
import com.comet.movieapp.data.model.MoviesDetailResponse
import com.comet.movieapp.data.model.PopularsMovieResponse
import com.comet.movieapp.data.remote.MoviesService
import javax.inject.Inject
import com.comet.movieapp.utils.Result
import com.comet.movieapp.utils.performNetworkFlow
import com.comet.movieapp.utils.toResultOrThrow
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(page: Int = 1): Flow<Result<PopularsMovieResponse>>
    suspend fun getUpcomingMovies(page: Int = 1): Flow<Result<PopularsMovieResponse>>
    suspend fun getMovieDetail(movieId: String): Flow<Result<MoviesDetailResponse>>
}

class MovieRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int) = performNetworkFlow {
        moviesService.getPopularMovies(API_KEY, LANGUAGE, page).toResultOrThrow()
    }

    override suspend fun getUpcomingMovies(page: Int) = performNetworkFlow {
        moviesService.getUpcomingMovies(API_KEY, LANGUAGE, page).toResultOrThrow()
    }

    override suspend fun getMovieDetail(movieId: String) = performNetworkFlow {
        moviesService.getMovieDetail(movieId, API_KEY, LANGUAGE).toResultOrThrow()
    }
}
