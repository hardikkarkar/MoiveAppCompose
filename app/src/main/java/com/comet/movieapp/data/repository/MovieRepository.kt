package com.comet.movieapp.data.repository

import com.comet.movieapp.common.Constants.API_KEY
import com.comet.movieapp.common.Constants.LANGUAGE
import com.comet.movieapp.data.model.PopularsMovieResponse
import com.comet.movieapp.data.remote.MoviesService
import javax.inject.Inject
import com.comet.movieapp.utils.Result
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovies(page: Int = 1): Result<Response<PopularsMovieResponse>>
}

class MovieRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Result<Response<PopularsMovieResponse>> {
        return try {
            val posts = moviesService.getPopularMovies(API_KEY, LANGUAGE, page)
            Result.Success(posts)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}
