package com.aghogho.pokemonapp.repository

import com.aghogho.pokemonapp.model.PokemonApi
import com.aghogho.pokemonapp.model.remotedata.ListOfPokemons
import com.aghogho.pokemonapp.utils.Resources
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonApi
) {

    suspend fun getAllPokemons(limit: Int, offset: Int): Resources<ListOfPokemons> {
        val response = try {
            api.getAllPokemon(limit, offset)
        } catch (exp: Exception) {
            return Resources.Error("Could not load pokemon...")
        }
        return Resources.Success(response)
    }

    suspend fun getPokemonDetail(pokemonName: String): Resources<ListOfPokemons> {
        val response = try {
            api.getPokemonDetail(pokemonName)
        } catch (exp: Exception) {
            return Resources.Error("Could not load pokemon data...")
        }
        return Resources.Success(response)
    }

}