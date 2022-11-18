package com.uli.rickandmortytest.ui.fragment.detailCharacter.state

import com.uli.rickandmortytest.domain.entity.Result

data class DetailCharacterState(
    val character: Result? = null,
    val characterIdFromCharacterListFragment: Int = 1,
)