package com.ibrahim.pokemonsample.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.pokemonsample.R
import com.ibrahim.pokemonsample.adapters.PokemonListAdapter
import com.ibrahim.pokemonsample.util.MainActivityRepository
import com.ibrahim.pokemonsample.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list){


    @Inject
    lateinit var pokemonListAdapter: PokemonListAdapter

    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        activity?.let {
            rvPokemonList.apply {
                layoutManager = LinearLayoutManager(it)
                adapter = pokemonListAdapter
                addItemDecoration(DividerItemDecoration(it, LinearLayoutManager(it).orientation))
            }
        }
    }

    private fun initObservers() {

    }


}