package com.ibrahim.pokemonsample.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ibrahim.pokemonsample.R
import com.ibrahim.pokemonsample.adapters.MyViewpagerAdapter
import kotlinx.android.synthetic.main.fragment_viewpager.*

class ViewPagerFragment : Fragment(R.layout.fragment_viewpager) {

    private var pagerAdapter : MyViewpagerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        activity?.let {
            pagerAdapter = MyViewpagerAdapter(it)
            viewPager.adapter = pagerAdapter
        }
        TabLayoutMediator(tabLayout, viewPager){ tab, position->
            tab.text = when(position){
                0 -> resources.getString(R.string.pokemon)
                else -> resources.getString(R.string.second_tab_title)
            }
        }.attach()
    }
}