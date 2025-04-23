package com.comet.movieapp.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.comet.movieapp.data.model.MovieDomain
import com.comet.movieapp.data.model.toDomainModel
import com.comet.movieapp.data.repository.MovieRepository
import com.comet.movieapp.utils.getOrThrow
import kotlinx.coroutines.flow.firstOrNull

class PopularMoviesPagingSource(private val movieRepository: MovieRepository) :
    PagingSource<Int, MovieDomain>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomain> {
        return try {
            val nextPageNumber = params.key ?: 1
            val movies = movieRepository.getPopularMovies(nextPageNumber).firstOrNull()
                ?.getOrThrow()?.results?.toDomainModel() ?: emptyList()

            LoadResult.Page(
                data = movies,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (movies.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
