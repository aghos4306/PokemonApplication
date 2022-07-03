package com.aghogho.pokemonapp.navigation

enum class PokemonScreens {
    SplashScreen,
    PokemonListScreen,
    PokemonDetailScreen;

    companion object {
        fun fromRoute(route: String?): PokemonScreens =
            when(route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                PokemonListScreen.name -> PokemonListScreen
                PokemonDetailScreen.name -> PokemonDetailScreen
                null -> PokemonListScreen
                else -> throw IllegalArgumentException("The route with name $route is not recongnized")
            }
    }
}