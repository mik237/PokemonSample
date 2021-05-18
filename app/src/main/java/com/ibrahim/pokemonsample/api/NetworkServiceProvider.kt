package com.ibrahim.pokemonsample.api

import com.ibrahim.pokemonsample.BuildConfig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkServiceProvider {

    fun getNetworkService() : NetworkService{
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(NetworkService::class.java)
    }

}