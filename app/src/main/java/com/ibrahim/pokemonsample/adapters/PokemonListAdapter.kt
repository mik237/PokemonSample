package com.ibrahim.pokemonsample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.pokemonsample.R
import com.ibrahim.pokemonsample.ui.data_model.PokemonDataModel
import javax.inject.Inject

class PokemonListAdapter @Inject constructor() : RecyclerView.Adapter<PokemonListAdapter.PokemonVH>() {

    private val pokemonList = ArrayList<PokemonDataModel>()
    private var listener : OnItemClickListener? = null

    fun setArticlesList(newArticlesList : List<PokemonDataModel>){
        val diffCallback = PokemonListDiffCallback(pokemonList, newArticlesList)
        val diffResult   = DiffUtil.calculateDiff(diffCallback)
        pokemonList.clear()
        pokemonList.addAll(newArticlesList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?){
        this.listener = listener
    }


    inner class PokemonVH(itemview : View) : RecyclerView.ViewHolder(itemview){
        fun bind(pokemonDataModel: PokemonDataModel){
            itemView.findViewById<TextView>(R.id.tvName).text = pokemonDataModel.name
            itemView.findViewById<TextView>(R.id.tvIdNumber).text = "${pokemonDataModel.id}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonVH(view)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
       holder.bind(pokemonList.get(position))
    }
}