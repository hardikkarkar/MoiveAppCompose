package com.comet.movieapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comet.movieapp.data.model.MovieDetailDomain
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
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val TAG: String = DetailViewModel::class.java.simpleName
    private val movieDetailFlow = MutableStateFlow(MovieDetailDomain())
    fun getMovieDetailFlow() = movieDetailFlow
    private val uiStateFlow = MutableStateFlow<String?>(null)
    fun getErrorFlow() = uiStateFlow

    fun getMovieDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            uiStateFlow.emit("Loading...")
            movieRepository.getMovieDetail(movieId)
                .catch {
                    Log.e(TAG, "error fetchPopularMovies: ${it.message}")
                    uiStateFlow.emit(it.message)
                }.collect {
                    val result = it.getOrThrow()
                    Log.d(TAG, "fetchPopularMovies: $result")
                    uiStateFlow.emit(null)
                    movieDetailFlow.emit(result.toDomainModel())
                }
        }
    }
}