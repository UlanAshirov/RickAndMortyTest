package com.uli.rickandmortytest.data.network.apiService

import com.uli.rickandmortytest.domain.entity.MainResponse
import com.uli.rickandmortytest.domain.entity.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacter(
        @Query("page") page: Int? = null
    ): Response<MainResponse<Result>>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Result
}