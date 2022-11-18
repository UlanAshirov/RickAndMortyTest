package com.uli.rickandmortytest.data.source

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.uli.rickandmortytest.data.network.apiService.RickAndMortyApi
import com.uli.rickandmortytest.domain.entity.Result
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject
const val START_PAGE = 1
class CharacterPagingSource @Inject constructor(private val api: RickAndMortyApi) :
    PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageNumber = params.key ?: START_PAGE
        return try {
            val response = api.getCharacter(page = pageNumber)
            var nextPageNumber: Int? = null
            if (response.body()?.info?.next != null) {
                val uri = Uri.parse(response.body()?.info?.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            return LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (pageNumber == START_PAGE) null else pageNumber - 1,
                nextKey = nextPageNumber
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}

