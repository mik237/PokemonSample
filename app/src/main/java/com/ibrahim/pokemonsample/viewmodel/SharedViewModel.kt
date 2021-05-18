package com.ibrahim.pokemonsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.pokemonsample.BuildConfig
import com.ibrahim.pokemonsample.api.Status
import com.ibrahim.pokemonsample.api.model.PokemonResult
import com.ibrahim.pokemonsample.api.model.ResponsePokemon
import com.ibrahim.pokemonsample.api.model.ResponsePokemonList
import com.ibrahim.pokemonsample.ui.data_model.PokemonDataModel
import com.ibrahim.pokemonsample.util.DataMapper
import com.ibrahim.pokemonsample.util.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val mainRepo: MainActivityRepository,
    private val dataMapper: DataMapper
) : ViewModel() {

    var mappedData = mutableListOf<PokemonDataModel>()
    val selectedPokemon = MutableLiveData<PokemonDataModel>()


    private val _pokemonListLiveData = MutableLiveData<Status<*>>()
    val pokemonLiveData : LiveData<Status<*>> = _pokemonListLiveData

    fun getPokemonList(limit : Int){

        viewModelScope.launch {
            mainRepo.getPokemonList(limit).collect {
                when(it){
                    is Status.Success ->{
                        val data = it.data as ResponsePokemonList
                        getPokemonFromUrl(data.results)
                    }
                    else -> {
                        _pokemonListLiveData.value = it
                    }
                }
            }
        }
    }

    private suspend fun getPokemonFromUrl(pokemonList: List<PokemonResult>){
        for(pokemonResult in pokemonList){
            var url = pokemonResult.url
            url = url.replace(BuildConfig.BASEURL, "")
            url = url.replace("pokemon/","")
            url = url.replace("/","")
            val id : Int = url.toInt()
            getPokemon(id)
        }
        _pokemonListLiveData.value = Status.Success(mappedData)
    }

    private suspend fun getPokemon(id : Int){
        mainRepo.getPokemon(id).collect {
            when(it){
                is Status.Success ->{
                    val data = it.data as ResponsePokemon
                    val pokemonDataModel = dataMapper.mapApiResponseToPokemonUIDataModel(data)
                    mappedData.add(pokemonDataModel)
                }
                else -> {}
            }
        }
    }
}