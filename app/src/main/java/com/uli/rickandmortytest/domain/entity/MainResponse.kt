package com.uli.rickandmortytest.domain.entity

data class MainResponse<T>(
    val info: Info,
    val results: List<T>
)