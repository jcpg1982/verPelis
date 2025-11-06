package pe.com.master.machines.verpelis.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import pe.com.master.machines.detail_character.navigation.DetailCharacterRoute
import pe.com.master.machines.detail_character.screen.DetailCharacterScreen
import pe.com.master.machines.home.navigation.HomeRoute
import pe.com.master.machines.home.screen.HomeScreen

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
                onNavigateToDetailCharacter = {
                    navController.navigate(DetailCharacterRoute(it))
                }
            )
        }

        composable<DetailCharacterRoute> { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<DetailCharacterRoute>()
            DetailCharacterScreen(
                characterId = detailRoute.id,
                onNavigateToBack = {
                    navController.popBackStack()
                }
            )
        }
    }

}