package com.aghogho.pokemonapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

        composable(PokemonScreens.PokemonDetailScreen.name) {
            PokemonDetailScreen(navController = navController)
        }
    }
}
