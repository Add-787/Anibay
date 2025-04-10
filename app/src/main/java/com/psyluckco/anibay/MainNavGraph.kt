/**
 * Created by developer on 09-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.psyluckco.anibay.home.HomeScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AnibayDestinations.HOME_ROUTE,
    navActions: AnibayNavigationActions = remember(navController) {
        AnibayNavigationActions(navController)
    },
    modifier: Modifier = Modifier
) {

    val currentNavBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            AnibayDestinations.HOME_ROUTE
        ) {
            HomeScreen(
                onAnimeClicked = { navActions.navigateToDetails(animeId = it.id)}
            )
        }
    }

}