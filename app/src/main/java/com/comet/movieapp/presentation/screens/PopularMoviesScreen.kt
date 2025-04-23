package com.comet.movieapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.comet.movieapp.presentation.composables.ErrorItem
import com.comet.movieapp.presentation.composables.ListMovieItem
import com.comet.movieapp.presentation.composables.LoadingItem
import com.comet.movieapp.presentation.viewmodels.PopularMoviesViewModel

@Composable
fun PopularMoviesScreen(
    onClickNavigateToDetails: (Int) -> Unit,
) {
    val popularMoviesViewModel = hiltViewModel<PopularMoviesViewModel>()
    val lazyMovieItems = popularMoviesViewModel.movies.collectAsLazyPagingItems()

    Box(Modifier) {
        Column {
            LazyColumn {
                items(lazyMovieItems.itemCount) { index ->
                    val movie = lazyMovieItems[index]
                    movie?.let {
                        ListMovieItem(
                            movieDomain = it,
                            onClick = { onClickNavigateToDetails(it.id) })
                    }
                }
                lazyMovieItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { LoadingItem() }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val e = lazyMovieItems.loadState.refresh as LoadState.Error
                            item {
                                ErrorItem(
                                    message = e.error.localizedMessage!!,
                                    modifier = Modifier.fillParentMaxSize(),
                                    onClickRetry = { retry() }
                                )
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            val e = lazyMovieItems.loadState.append as LoadState.Error
                            item {
                                ErrorItem(
                                    message = e.error.localizedMessage!!,
                                    onClickRetry = { retry() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


