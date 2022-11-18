package com.uli.rickandmortytest.domain.useCase

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.uli.rickandmortytest.domain.entity.Result
import com.uli.rickandmortytest.domain.repository.RickRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(private val repository: RickRepository) {
     suspend fun getCharacter(): Flow<PagingData<Result>> {
        return repository.getCharacter()
    }
}