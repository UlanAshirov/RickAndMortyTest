package com.uli.rickandmortytest.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.uli.rickandmortytest.data.network.apiService.RickAndMortyApi
import com.uli.rickandmortytest.data.source.CharacterPagingSource
import com.uli.rickandmortytest.domain.entity.Result
import com.uli.rickandmortytest.domain.repository.RickRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickRepositoryImpl @Inject
constructor(private val api: RickAndMortyApi) :
    RickRepository {

    override suspend fun getCharacter(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = 25),
            pagingSourceFactory = {
                CharacterPagingSource(
                    api
                )
            }
        ).flow
    }


    override suspend fun getCharacterById(id: Int): Result {
        return api.getCharacterById(id)
    }
}