package com.uli.rickandmortytest.ui.fragment.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uli.rickandmortytest.R
import com.uli.rickandmortytest.base.BaseFragment
import com.uli.rickandmortytest.databinding.FragmentHomeBinding
import com.uli.rickandmortytest.ui.fragment.home.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    CharacterAdapter.OpenDetailListener {
    private lateinit var adapter: CharacterAdapter
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    override fun setupUI() {
        adapter = CharacterAdapter(this)
        binding.rvCharacter.adapter = adapter
        binding.viewModel = viewModel
    }

    override fun setupObserver() {
        super.setupObserver()
        lifecycleScope.launch {
            viewModel.getCharacters().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun openDetailCharacter(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        controller.navigate(R.id.detailCharacterFragment, bundle)
    }
}