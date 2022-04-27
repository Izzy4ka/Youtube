package com.example.youtube.data.item_play_list.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.youtube.domain.item_play_list.entity.ItemPlayList
import com.example.youtube.domain.item_play_list.entity.ItemPlayerList
import java.lang.Exception

typealias ItemPlayListLoader = suspend (pageToken: String) -> ItemPlayList

class ItemPlayListPagingSource
    (private val itemPlayListLoader: ItemPlayListLoader) : PagingSource<String, ItemPlayerList>() {
    override fun getRefreshKey(state: PagingState<String, ItemPlayerList>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ItemPlayerList> {
        val pageToken = params.key ?: ""
        return try {
            val result = itemPlayListLoader.invoke(pageToken)
            return LoadResult.Page(
                data = result.items!!,
                prevKey = result.prevPageToken,
                nextKey = result.nextPageToken
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

}