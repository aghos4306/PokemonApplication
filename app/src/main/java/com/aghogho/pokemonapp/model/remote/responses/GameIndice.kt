package com.aghogho.pokemonapp.model.remote.responses


import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int,
    @SerializedName("version")
    val version: Version
)