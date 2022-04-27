package com.example.youtube.data.playlists.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.youtube.data.playlists.remote.ApiPlayList
import com.example.youtube.data.playlists.source.PlayListPageLoader
import com.example.youtube.data.playlists.source.PlayListPagingSource
import com.example.youtube.domain.play_list.entity.ItemPlayer
import com.example.youtube.domain.play_list.entity.PlayList
import com.example.youtube.domain.play_list.repository.PlayListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PlayListRepositoryImpl(private val apiPlayList: ApiPlayList) : PlayListRepository {

    override suspend fun getPlayList(): Flow<PagingData<ItemPlayer>> {
        val loader: PlayListPageLoader = { pageToken, maxResult ->
            getPlayList(pageToken, maxResult)
        }
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PlayListPagingSource(loader) }
        ).flow
    }


    private suspend fun getPlayList(pageToken: String, maxResult: Int): PlayList =
        withContext(Dispatchers.IO) {
            val result = apiPlayList.getPlayList(
                pageToken = pageToken,
                max = maxResult
            )
            return@withContext result.body()!!
        }

}