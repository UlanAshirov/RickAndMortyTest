package com.uli.rickandmortytest.domain.entity

data class Result(
    val created: String? = null,
    val episode: List<String>? = null,
    val gender: String? = null,
    val id: Int? = null,
    val image: String,
    val location: Location? = null,
    val name: String? = null,
    val species: String? = null,
    val status: String? = null,
    val type: String? = null,
    val url: String? = null
)