package com.uli.rickandmortytest.domain.useCase

import com.uli.rickandmortytest.domain.entity.Result
import com.uli.rickandmortytest.domain.repository.RickRepository

class GetCharacterById(private val repo: RickRepository) {
   suspend fun getCharacterById(id: Int): Result {
        return repo.getCharacterById(id)
    }
}