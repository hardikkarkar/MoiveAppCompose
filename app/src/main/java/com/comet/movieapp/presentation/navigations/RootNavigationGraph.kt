package com.comet.movieapp.presentation.navigations

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.comet.movieapp.R
import com.comet.movieapp.presentation.screens.DetailScreen
import com.comet.movieapp.presentation.screens.PopularMoviesScreen
import com.comet.movieapp.presentation.screens.TrendingMoviesScreen
import com.comet.movieapp.presentation.screens.UpcomingMoviesScreen

object Graph {
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen("information_screen")
}

sealed class HomeScreen(val route: String, val icon: Int, val title: String) {
    object TrendingScreen : HomeScreen("trending_screen", R.drawable.ic_favorite, "Trending")
    object PopularHomeScreen : HomeScreen("popular_screen", R.drawable.ic_movie, "Popular")
    object UpcomingHomeScreen : HomeScreen("upcoming_screen", R.drawable.ic_love, "Upcoming")
}

@Composable
fun homeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreen.TrendingScreen.route,
    ) {
        composable(HomeScreen.TrendingScreen.route) {
            TrendingMoviesScreen(onClickNavigateToDetails = { movieID ->
                navController.navigate(route = Graph.DETAILS + "/$movieID")
            })
        }
        composable(HomeScreen.PopularHomeScreen.route) {
            PopularMoviesScreen(onClickNavigateToDetails = { movieID ->
                navController.navigate(route = Graph.DETAILS + "/$movieID")
            })
        }
        composable(HomeScreen.UpcomingHomeScreen.route) {
            UpcomingMoviesScreen(onClickNavigateToDetails = { movieID ->
                navController.navigate(route = Graph.DETAILS + "/$movieID")
            })
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS + "/{movieId}",
        startDestination = DetailsScreen.Information.route
    ) {
        composable(DetailsScreen.Information.route) {
            val movieId = it.arguments?.getString("movieId") ?: ""
            Log.d("detailsNavGraph", "movieId retrieved: ${movieId}")
            DetailScreen(movieId)
        }
    }
}
