package com.comet.movieapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.comet.movieapp.data.model.MovieDomain
import com.comet.movieapp.data.repository.MovieRepository
import com.comet.movieapp.domain.TrendingMoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<PagingData<MovieDomain>>(PagingData.empty())
    val movies: StateFlow<PagingData<MovieDomain>> = _movies

    init {
        viewModelScope.launch {
            getTrendingAll()
        }
    }

    private suspend fun getTrendingAll() {
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { TrendingMoviesPagingSource(movieRepository) }
        ).flow.cachedIn(viewModelScope).collect{
            _movies.value = it
        }
    }

    companion object {
        private val TAG: String = TrendingMoviesViewModel::class.java.simpleName
        private const val PAGE_SIZE = 20
    }
}

