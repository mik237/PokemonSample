package com.ibrahim.pokemonsample.api.model
import com.google.gson.annotations.SerializedName



data class ResponsePokemonList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<PokemonResult>
)

data class PokemonResult(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)