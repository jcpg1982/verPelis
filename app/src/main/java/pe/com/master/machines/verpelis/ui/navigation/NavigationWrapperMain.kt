package pe.com.master.machines.verpelis.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import pe.com.master.machines.detail_character.navigation.DetailCharacterRoute
import pe.com.master.machines.home.navigation.HomeRoute
import pe.com.master.machines.home.screen.HomeScreen
import pe.com.master.machines.view_episodes.navigation.ViewEpisodesRoute
import pe.com.master.machines.view_episodes.screen.ViewEpisodesScreen

@Composable
fun NavigationWrapperMain(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onNavigateToViewEpisodes = {
                    navController.navigate(ViewEpisodesRoute(it))
                },
                onNavigateToDetailCharacter = {
                    navController.navigate(DetailCharacterRoute(it))
                }
            )
        }

        /*composable<DetailCharacterRoute> { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<DetailCharacterRoute>()
            DetailCharacterScreen(
                characterId = detailRoute.id,
                onNavigateToViewEpisodes = {
                    navController.navigate(ViewEpisodesRoute(it))
                },
                onNavigateToBack = {
                    navController.popBackStack()
                }
            )
        }*/

        composable<ViewEpisodesRoute> { backStackEntry ->
            val viewEpisodesRoute = backStackEntry.toRoute<ViewEpisodesRoute>()
            ViewEpisodesScreen(
                ids = viewEpisodesRoute.ids,
                onNavigateToBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}