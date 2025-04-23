package com.comet.movieapp.presentation.navigations

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val menuItems = listOf(
        HomeScreen.TrendingScreen,
        HomeScreen.PopularHomeScreen,
        HomeScreen.UpcomingHomeScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = menuItems.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar(
            modifier = Modifier.height(80.dp),
            containerColor = Color.Black.copy(alpha = 0.8f),
        ) {
            menuItems.forEach { screen ->
                //setup the alpha for the selected item
                val isSelectedMenu =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true
                val backgroundAlpha = if (isSelectedMenu) 1f else 0.7f

                NavigationBarItem(
                    label = {
                        Text(text = screen.title, color = Color.White.copy(alpha = backgroundAlpha))
                    },
                    icon = {
                        Icon(
                            painterResource(id = screen.icon),
                            contentDescription = screen.title,
                            modifier = Modifier.graphicsLayer(alpha = backgroundAlpha)
                        )
                    },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    })
            }
        }
    }
}
