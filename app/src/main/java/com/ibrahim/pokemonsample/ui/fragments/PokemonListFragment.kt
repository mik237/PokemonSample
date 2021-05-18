package com.ibrahim.pokemonsample.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.pokemonsample.R
import com.ibrahim.pokemonsample.adapters.OnItemClickListener
import com.ibrahim.pokemonsample.adapters.PokemonListAdapter
import com.ibrahim.pokemonsample.api.Status
import com.ibrahim.pokemonsample.ui.data_model.PokemonDataModel
import com.ibrahim.pokemonsample.util.Constants
import com.ibrahim.pokemonsample.util.MainActivityRepository
import com.ibrahim.pokemonsample.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list), OnItemClickListener {


    @Inject
    lateinit var pokemonListAdapter: PokemonListAdapter

    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()

        fetchPokemonList()

    }

    private fun fetchPokemonList() {
        sharedViewModel.getPokemonList(Constants.LIMIT)
    }

    private fun initViews() {
        activity?.let {
            rvPokemonList.apply {
                layoutManager = LinearLayoutManager(it)
                adapter = pokemonListAdapter
                addItemDecoration(DividerItemDecoration(it, LinearLayoutManager(it).orientation))
            }
        }
        pokemonListAdapter.setOnItemClickListener(this)
    }

    private fun initObservers() {
        sharedViewModel.pokemonLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {status ->
                when(status){
                    is Status.Loading -> {
                        progressBar.isVisible = status.showLoading
                    }

                    is Status.Error -> {
                        status.errorMsg?.let {
                            rvPokemonList.isVisible = false
                            tvError.apply {
                                isVisible = true
                                text = it
                            }
                        }
                    }

                    is Status.Success -> {
                        status.data?.let { data ->
                            rvPokemonList.isVisible = true
                            tvError.isVisible = false
                            val articlesList : List<PokemonDataModel> = data as List<PokemonDataModel>
                            if(articlesList.isNotEmpty())
                                pokemonListAdapter.setArticlesList(articlesList)
                        }
                    }
                }
            }
        })
    }

    override fun onItemClicked(pokemonDataModel: PokemonDataModel) {
        sharedViewModel.selectedPokemon.value = pokemonDataModel
        findNavController().navigate(R.id.action_viewPagerFragment_to_pokemonDetailFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        rvPokemonList.adapter = null
        pokemonListAdapter.setOnItemClickListener(null)
    }
}