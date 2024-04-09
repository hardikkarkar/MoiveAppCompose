package com.comet.movieapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comet.movieapp.data.model.MovieDomain
import com.comet.movieapp.data.model.MovieEntity
import com.comet.movieapp.data.model.toDomainModel
import com.comet.movieapp.data.repository.MovieRepository
import com.comet.movieapp.utils.getOrThrow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val TAG: String = PopularMoviesViewModel::class.java.simpleName
    private val popularMoviesFlow = MutableStateFlow<List<MovieDomain>>(emptyList())
    fun getPopularMoviesFlow() = popularMoviesFlow
    private val uiStateFlow = MutableStateFlow<String?>(null)
    fun getErrorFlow() = uiStateFlow

    fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            uiStateFlow.emit("Loading...")
            movieRepository.getPopularMovies()
                .catch {
                    Log.e(TAG, "error fetchPopularMovies: ${it.message}")
                    uiStateFlow.emit(it.message)
                }.collect {
                    val result = it.getOrThrow()
                    Log.d(TAG, "fetchPopularMovies: $result")
                    uiStateFlow.emit(null)
                    popularMoviesFlow.emit(result.results.toDomainModel())
                }
        }
    }
}