package com.aghogho.pokemonapp.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.aghogho.pokemonapp.model.pokemodel.PokemonListEntry
import com.aghogho.pokemonapp.repository.PokemonRepository
import com.aghogho.pokemonapp.utils.Constants.PAGE_SIZE
import com.aghogho.pokemonapp.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    var curPage = 0

    var pokemonnlist = mutableStateOf<List<PokemonListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedPokemonList = listOf<PokemonListEntry>()
    var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    fun searchPokemonList(query: String) {
        val listToSearch = if (isSearchStarting) {
            pokemonnlist.value
        } else {
            cachedPokemonList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                pokemonnlist.value = cachedPokemonList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.pokemonName.contains(query.trim(), ignoreCase = true) ||
                        it.number.toString() == query.trim()
            }
            if (isSearchStarting) {
                cachedPokemonList = pokemonnlist.value
                isSearchStarting = false
            }
            pokemonnlist.value = results
            isSearching.value = true
        }

    }

    fun loadPokemonPagination() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getAllPokemons(PAGE_SIZE, curPage * PAGE_SIZE)
            when(result) {
                is Resources.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count
                    val pokemonEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonnlist.value += pokemonEntries
                }
                is Resources.Error -> {
                    loadError.value = result.message.toString()
                    isLoading.value = false
                }
            }
        }
    }

    fun calculateDominantColour(
        drawable: Drawable, onFinish: (Color) -> Unit
    ) {
        val bmpColor = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmpColor).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colourValue ->
                onFinish(Color(colourValue))
            }
        }
    }

}