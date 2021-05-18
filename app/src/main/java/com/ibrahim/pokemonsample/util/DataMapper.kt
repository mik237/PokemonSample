package com.ibrahim.pokemonsample.util

import com.ibrahim.pokemonsample.api.model.ResponsePokemon
import com.ibrahim.pokemonsample.ui.data_model.PokemonDataModel
import javax.inject.Inject

class DataMapper @Inject constructor() {
    fun mapApiResponseToPokemonUIDataModel(response : ResponsePokemon) : PokemonDataModel{
        return PokemonDataModel(
            id = response.id
        )
    }
}