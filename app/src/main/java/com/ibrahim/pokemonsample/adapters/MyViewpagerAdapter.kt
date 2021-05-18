package com.ibrahim.pokemonsample.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ibrahim.pokemonsample.ui.fragments.PokemonListFragment
import com.ibrahim.pokemonsample.ui.fragments.WebviewFragment

class MyViewpagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when(position){
            1 ->{return WebviewFragment()}
            else ->{
                return PokemonListFragment()
            }
        }
    }
}