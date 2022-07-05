package com.aghogho.pokemonapp.viewmodel

import androidx.lifecycle.ViewModel
import com.aghogho.pokemonapp.model.remote.responses.Pokemon
import com.aghogho.pokemonapp.repository.PokemonRepository
import com.aghogho.pokemonapp.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    suspend fun getPokemonDetail(pokemonName: String): Resources<Pokemon> {
        return repository.getPokemonDetail(pokemonName)
    }

}