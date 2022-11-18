package com.uli.rickandmortytest.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.uli.rickandmortytest.data.common.Resource
import com.uli.rickandmortytest.domain.entity.MainResponse
import com.uli.rickandmortytest.domain.entity.Result
import kotlinx.coroutines.flow.Flow


interface RickRepository {
    suspend fun getCharacter(): Flow<PagingData<Result>>
    suspend fun getCharacterById(id: Int): Result
}