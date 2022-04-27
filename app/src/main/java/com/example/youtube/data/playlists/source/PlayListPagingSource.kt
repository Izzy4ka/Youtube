package com.example.youtube.data.playlists.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.youtube.domain.play_list.entity.ItemPlayer
import com.example.youtube.domain.play_list.entity.PlayList
import java.lang.Exception

typealias PlayListPageLoader = suspend (pageToken: String, maxResult: Int) -> PlayList

class PlayListPagingSource(
    private val loader: PlayListPageLoader,
) : PagingSource<String, ItemPlayer>() {
    override fun getRefreshKey(state: PagingState<String, ItemPlayer>): String? {
        return null
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ItemPlayer> {
        val pageToken = params.key ?: ""
        return try {
            val itemPlayList = loader.invoke(pageToken, params.loadSize)
            return LoadResult.Page(
                data = itemPlayList.items!!,
                prevKey = itemPlayList.prevPageToken,
                nextKey = itemPlayList.nextPageToken
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }
}