package com.uli.rickandmortytest.ui.fragment.detailCharacter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uli.rickandmortytest.data.common.Resource
import com.uli.rickandmortytest.data.network.repository.RickRepositoryImpl
import com.uli.rickandmortytest.domain.entity.Result
import com.uli.rickandmortytest.domain.useCase.GetCharacterById
import com.uli.rickandmortytest.domain.useCase.GetCharacterUseCase
import com.uli.rickandmortytest.ui.fragment.detailCharacter.state.DetailCharacterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(repository: RickRepositoryImpl) : ViewModel() {
        private val getCharacterByIdUseCase = GetCharacterById(repository)
    private val _state = MutableStateFlow(DetailCharacterState())
    val state: StateFlow<DetailCharacterState> get() = _state

    private fun getCharacterId(id: Int) {
        viewModelScope.launch {
            val data = getCharacterByIdUseCase.getCharacterById(id)
            _state.value = _state.value.copy(
                character = data
            )
        }
    }

    fun getDetailCharacter() {
        getCharacterId(getId())
    }

    fun setId(id: Int) {
        _state.value = _state.value.copy(
            characterIdFromCharacterListFragment = id
        )
    }

    private fun getId(): Int {
        return _state.value.characterIdFromCharacterListFragment
    }
}