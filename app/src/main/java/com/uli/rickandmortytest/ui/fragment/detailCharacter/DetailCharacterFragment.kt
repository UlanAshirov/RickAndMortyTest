package com.uli.rickandmortytest.ui.fragment.detailCharacter

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.uli.rickandmortytest.R
import com.uli.rickandmortytest.base.BaseFragment
import com.uli.rickandmortytest.databinding.FragmentDetailCharacterBinding
import com.uli.rickandmortytest.ui.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCharacterFragment :
    BaseFragment<FragmentDetailCharacterBinding>(R.layout.fragment_detail_character) {
    private var characterId = 0
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[DetailCharacterViewModel::class.java]
    }

    override fun setupUI() {
        binding.viewModel = viewModel
        if (arguments != null) {
            characterId = arguments?.getInt("id")!!
            viewModel.setId(characterId)
            viewModel.getDetailCharacter()
        } else {
            requireActivity().showToast("Not id")
        }
    }
}