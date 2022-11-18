package com.uli.rickandmortytest.ui.fragment.home.state

import androidx.paging.PagingData
import com.uli.rickandmortytest.domain.entity.Result

data class CharacterState(
    val resutls: PagingData<Result>? = PagingData.empty(),
    val resultsIdFromResultListFragment: Int = 1,
    val showToastMessageEvent: Boolean = false,
    )
