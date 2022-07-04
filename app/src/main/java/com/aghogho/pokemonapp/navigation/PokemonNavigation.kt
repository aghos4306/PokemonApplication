package com.aghogho.pokemonapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aghogho.pokemonapp.screens.PokemonDetailScreen
import com.aghogho.pokemonapp.screens.PokemonListScreen
import com.aghogho.pokemonapp.screens.PokemonSplashScreen

@Composable
fun PokemonNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokemonScreens.SplashScreen.name
    ) {
        composable(PokemonScreens.SplashScreen.name) {
            PokemonSplashScreen(navController = navController)
        }

        composable(PokemonScreens.PokemonListScreen.name) {
            PokemonListScreen(navController = navController)
        }

        //set route to navigate to specific pokemon
        val pokemonName = PokemonScreens.PokemonDetailScreen.name
        composable("$pokemonName/{pokemonId}", arguments = listOf(navArgument("pokemonId") {
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("pokemonId").let {
                PokemonDetailScreen(navController = navController, pokemonId = it.toString())
            }
        }
    }
}
