package com.ibrahim.pokemonsample.api

import com.ibrahim.pokemonsample.api.model.ResponsePokemon
import com.ibrahim.pokemonsample.api.model.ResponsePokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit : Int
    ) : Response<ResponsePokemonList>


    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id : Int
    ) : Response<ResponsePokemon>
}