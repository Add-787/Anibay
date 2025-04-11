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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.psyluckco.anibay.AnibayDestinationArgs.ANIME_ID_ARG
import com.psyluckco.anibay.details.DetailsScreen
import com.psyluckco.anibay.home.HomeScreen
import java.util.Map.entry

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
                onAnimeClicked = { navActions.navigateToDetails(animeId = it)}
            )
        }

        composable(
            AnibayDestinations.DETAILS_ROUTE,
            arguments = listOf(
                navArgument(ANIME_ID_ARG) { type = NavType.IntType }
            )
        ) {
            entry ->
                val animeId = entry.arguments?.getInt(ANIME_ID_ARG)

            DetailsScreen(
                animeId = animeId,
                onBackClicked = { navController.popBackStack() }
            )

        }
    }

}