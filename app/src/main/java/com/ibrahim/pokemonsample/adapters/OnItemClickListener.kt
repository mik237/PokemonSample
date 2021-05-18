package com.ibrahim.pokemonsample.adapters

import com.ibrahim.pokemonsample.ui.data_model.PokemonDataModel

interface OnItemClickListener {
    fun onItemClicked(pokemonDataModel: PokemonDataModel)
}