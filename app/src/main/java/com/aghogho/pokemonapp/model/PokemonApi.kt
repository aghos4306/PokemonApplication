package com.aghogho.pokemonapp.model

import com.aghogho.pokemonapp.model.remotedata.ListOfPokemons
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ListOfPokemons

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): ListOfPokemons

}