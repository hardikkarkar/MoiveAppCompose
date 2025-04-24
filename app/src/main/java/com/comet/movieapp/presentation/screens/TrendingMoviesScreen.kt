package com.comet.movieapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.comet.movieapp.presentation.composables.ErrorItem
import com.comet.movieapp.presentation.composables.MovieItem
import com.comet.movieapp.presentation.composables.LoadingItem
import com.comet.movieapp.presentation.composables.TrendingItem
import com.comet.movieapp.presentation.viewmodels.TrendingMoviesViewModel

@Composable
fun TrendingMoviesScreen(
    onClickNavigateToDetails: (Int) -> Unit,
) {
    val viewModel = hiltViewModel<TrendingMoviesViewModel>()
    val lazyMovieItems = viewModel.movies.collectAsLazyPagingItems()

    Box(Modifier) {
        Column {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(lazyMovieItems.itemCount) { index ->
                    val movie = lazyMovieItems[index]
                    movie?.let {
                        TrendingItem(
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
                                    modifier = Modifier.fillMaxSize(),
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


