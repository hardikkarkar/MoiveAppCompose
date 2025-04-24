package com.comet.movieapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.comet.movieapp.data.model.MovieDomain
import com.comet.movieapp.data.model.toDomainModel
import com.comet.movieapp.data.repository.MovieRepository
import com.comet.movieapp.utils.getOrThrow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: Flow<String> = _searchQuery

    private val movieFlow = MutableStateFlow(emptyList<MovieDomain>())
    fun getMovieFlow(): StateFlow<List<MovieDomain>> = movieFlow

    private val uiStateFlow = MutableStateFlow<String?>(null)
    fun getErrorFlow(): StateFlow<String?> = uiStateFlow

    fun updateQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchMovies(query: String): Flow<Unit> = flow {
        uiStateFlow.emit("Loading...")
        movieRepository.searchMovies(query)
            .catch {
                Log.e(TAG, "error search movie: ${it.message}")
                uiStateFlow.emit(it.message)
            }.collect {
                val result = it.getOrThrow()
                Log.d(TAG, "fetchPopularMovies: $result")
                uiStateFlow.emit(null)
                movieFlow.emit(result.results.toDomainModel())
                emit(Unit)
            }
    }.catch {
        Log.e(TAG, "error search movie: ${it.message}")
        uiStateFlow.emit(it.message)
    }

    companion object {
        val TAG = SearchViewModel::class.java.simpleName
    }
}