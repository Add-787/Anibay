/**
 * Created by developer on 09-04-2025.
 * Tismo Technology Solutions (P) Ltd.
 * developers@tismotech.net
 */

package com.psyluckco.anibay

import androidx.navigation.NavHostController
import com.psyluckco.anibay.AnibayDestinationArgs.ANIME_ID_ARG
import com.psyluckco.anibay.AnibayScreens.DETAILS_SCREEN
import com.psyluckco.anibay.AnibayScreens.HOME_SCREEN


/**
 * Screens used in [AnibayDestinations]
 */
private object AnibayScreens {
    const val HOME_SCREEN = "home"
    const val DETAILS_SCREEN = "details"
}

/**
 * Arguments used in [AnibayDestinations] routes
 */
object AnibayDestinationArgs {
    const val ANIME_ID_ARG = "animeId"
}

/**
 * Destinations used in the MainActivity
 */
object AnibayDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val DETAILS_ROUTE = "$DETAILS_SCREEN?$ANIME_ID_ARG={$ANIME_ID_ARG}"
}

/**
 * Models the navigation actions in the app
 */
class AnibayNavigationActions(private val navController: NavHostController) {

    fun navigateToDetails(animeId: Int?) {
        navController.navigate(
            DETAILS_SCREEN.let {
                if(animeId != null) "$it?$ANIME_ID_ARG=$animeId" else it
            }
        )
    }
}