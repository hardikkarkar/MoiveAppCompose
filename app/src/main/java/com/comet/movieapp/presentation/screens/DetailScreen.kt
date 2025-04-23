package com.comet.movieapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comet.movieapp.presentation.composables.DetailContent
import com.comet.movieapp.presentation.composables.UpdateUiStateMessage
import com.comet.movieapp.presentation.viewmodels.DetailViewModel

@Composable
fun DetailScreen(
    movieId: String
) {
    Box {
        val detailViewModel = hiltViewModel<DetailViewModel>()
        val movieDetailDomainState = detailViewModel.getMovieDetailFlow().collectAsState()
        val uiState = detailViewModel.getErrorFlow().collectAsState()
        LaunchedEffect(Unit) {
            detailViewModel.getMovieDetails(movieId)
        }
        DetailContent(movieDetailDomainState.value)
        UpdateUiStateMessage(state = uiState)
    }
}
