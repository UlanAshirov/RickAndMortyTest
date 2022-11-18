package com.uli.rickandmortytest.ui.fragment.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.uli.rickandmortytest.data.common.Resource
import com.uli.rickandmortytest.data.network.repository.RickRepositoryImpl
import com.uli.rickandmortytest.domain.entity.MainResponse
import com.uli.rickandmortytest.domain.entity.Result
import com.uli.rickandmortytest.domain.useCase.GetCharacterUseCase
import com.uli.rickandmortytest.ui.fragment.home.state.CharacterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repo: RickRepositoryImpl) : ViewModel() {
    private val getCharacterUseCase = GetCharacterUseCase(repo)
    private val _state = MutableStateFlow(CharacterState())
    val state: StateFlow<CharacterState> get() = _state

    init {
        viewModelScope.launch {
            getCharacters().collect {
                _state.value = _state.value.copy(resutls = it)
            }
        }
    }

     suspend fun getCharacters(): Flow<PagingData<Result>> {
        return getCharacterUseCase.getCharacter()
    }
}