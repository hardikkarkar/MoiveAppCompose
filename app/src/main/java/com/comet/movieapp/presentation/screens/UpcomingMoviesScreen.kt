package com.comet.movieapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comet.movieapp.presentation.composables.ListMovieItem
import com.comet.movieapp.presentation.composables.UpdateUiStateMessage
import com.comet.movieapp.presentation.viewmodels.UpcomingMoviesViewModel

@Composable
fun UpcomingMoviesScreen(
    onClickNavigateToDetails: (Int) -> Unit,
) {
    Box(Modifier.padding(bottom = 80.dp)) {
        val popularMoviesViewModel = hiltViewModel<UpcomingMoviesViewModel>()
        val popularMovies = popularMoviesViewModel.getUpcomingMoviesFlow().collectAsState()
        val uiState = popularMoviesViewModel.getErrorFlow().collectAsState()
        LaunchedEffect(Unit) {
            popularMoviesViewModel.fetchUpcomingMovies()
        }
        Column {
            UpdateUiStateMessage(state = uiState)
            LazyColumn {
                items(popularMovies.value) {
                    ListMovieItem(movieDomain = it, onClick = { onClickNavigateToDetails(it.id) })
                }
            }
        }
    }
}
