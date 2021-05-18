package com.ibrahim.pokemonsample.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ibrahim.pokemonsample.ui.data_model.PokemonDataModel

class PokemonListDiffCallback(private val oldList: List<PokemonDataModel>, private val newList: List<PokemonDataModel>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}