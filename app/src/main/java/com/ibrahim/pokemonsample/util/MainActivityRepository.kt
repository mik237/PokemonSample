package com.ibrahim.pokemonsample.util

import com.ibrahim.pokemonsample.api.NetworkService
import com.ibrahim.pokemonsample.api.networkBoundResource
import javax.inject.Inject

class MainActivityRepository @Inject constructor(
    private val networkService: NetworkService,
    private val connectionManager: ConnectionManager
){

    fun getPokemonList(limit : Int) = networkBoundResource(
        isNetworkConnected = {connectionManager.isConnected()},
        fetch = {
            networkService.getPokemonList(limit)
        }
    )

    fun getPokemon(id : Int) = networkBoundResource(
        isNetworkConnected = {connectionManager.isConnected()},
        fetch = {
            networkService.getPokemon(id)
        }
    )

   /* suspend fun getPokemonList(limit : Int)*//* : Status<*>*//*{
        val response = networkService.getPokemonList(limit)
        if(response.isSuccessful){
            Log.d("TAG", "getPokemonList: ${response.body()}")
            response.body()?.let {
                getPokemonFromUrl(it.results)
            }
        }
        else{
            Log.d("TAG", "getPokemonList: ${response.code()}")
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
    }

    private suspend fun getPokemon(id : Int)*//* : Status<*>*//*{
        val response = networkService.getPokemon(id)
        if(response.isSuccessful){
            Log.d("TAG", "getPokemon: ${response.body()}")
            response.body()?.let {
                val pokemonDataModel = dataMapper.mapApiResponseToPokemonUIDataModel(it)
            }
        }
        else{
            Log.d("TAG", "getPokemon: ${response.code()}")
        }
    }*/
}