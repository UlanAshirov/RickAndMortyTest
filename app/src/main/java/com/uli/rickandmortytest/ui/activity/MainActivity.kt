package com.uli.rickandmortytest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.uli.rickandmortytest.databinding.ActivityMainBinding
import com.uli.rickandmortytest.ui.utils.isOnline
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        chekInternet()
    }

    private fun chekInternet() {
        if (isOnline()) {
            binding.chekInternet.rootView.isGone
        } else {
            binding.chekInternet.rootView.isVisible
        }
    }
}