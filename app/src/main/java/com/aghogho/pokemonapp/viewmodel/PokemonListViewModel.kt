package com.aghogho.pokemonapp.viewmodel

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import com.aghogho.pokemonapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

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