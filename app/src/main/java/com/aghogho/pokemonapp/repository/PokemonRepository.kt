package com.aghogho.pokemonapp.repository

import com.aghogho.pokemonapp.model.PokemonApi
import com.aghogho.pokemonapp.model.remote.responses.Pokemon
import com.aghogho.pokemonapp.model.remote.responses.PokemonList
import com.aghogho.pokemonapp.utils.Resources
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokemonApi
) {

    suspend fun getAllPokemon(limit: Int, offset: Int): Resources<PokemonList> {
        val response = try {
            api.getAllPokemon(limit, offset)
        } catch (exp: Exception) {
            return Resources.Error("Could not load pokemon...")
        }
        return Resources.Success(response)
    }

    suspend fun getPokemonDetail(pokemonName: String): Resources<Pokemon> {
        val response = try {
            api.getPokemonDetail(pokemonName)
        } catch (exp: Exception) {
            return Resources.Error("Could not load pokemon data...")
        }
        return Resources.Success(response)
    }

}