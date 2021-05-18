package com.ibrahim.pokemonsample.ui.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ibrahim.pokemonsample.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}