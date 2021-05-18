package com.ibrahim.pokemonsample.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ibrahim.pokemonsample.R
import com.ibrahim.pokemonsample.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_pokeman_detail.*

class PokemonDetailFragment : Fragment(R.layout.fragment_pokeman_detail){

    private val sharedViewModel : SharedViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        sharedViewModel.selectedPokemon.observe(viewLifecycleOwner, Observer {
            it?.let {pokemonDataModel ->
                tvName.text = pokemonDataModel.name
                tvExperience.text = "${resources.getString(R.string.experience)}: ${pokemonDataModel.baseExperience}"
                tvWeight.text = "${resources.getString(R.string.weight)}: ${pokemonDataModel.weight}"

                if(pokemonDataModel.largeImgUrl.isNotEmpty()){
                    Glide.with(ivPokemanImage)
                        .load(pokemonDataModel.largeImgUrl)
                        .into(ivPokemanImage)
                }
            }
        })
    }

    private fun initViews() {
        toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}