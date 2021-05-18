package com.ibrahim.pokemonsample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.pokemonsample.R
import javax.inject.Inject

class PokemonListAdapter @Inject constructor() : RecyclerView.Adapter<PokemonListAdapter.PokemonVH>() {

    inner class PokemonVH(itemview : View) : RecyclerView.ViewHolder(itemview){
        fun onBind(){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonVH(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        TODO("Not yet implemented")
    }
}