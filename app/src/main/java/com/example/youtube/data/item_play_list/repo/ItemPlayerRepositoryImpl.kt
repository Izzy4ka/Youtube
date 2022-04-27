package com.example.youtube.data.item_play_list.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.youtube.data.item_play_list.remote.ItemPlayListApi
import com.example.youtube.data.item_play_list.source.ItemPlayListLoader
import com.example.youtube.data.item_play_list.source.ItemPlayListPagingSource
import com.example.youtube.domain.item_play_list.entity.ItemPlayList
import com.example.youtube.domain.item_play_list.entity.ItemPlayerList
import com.example.youtube.domain.item_play_list.repository.ItemPlayerRepository
import com.example.youtube.domain.play_list.entity.PlayList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ItemPlayerRepositoryImpl(
    private val itemApi: ItemPlayListApi
) : ItemPlayerRepository {
    override suspend fun getPlayList(id: String): Flow<PlayList> {
        return flow {
            val result = itemApi.getPlayList(id = id)
            if (result.isSuccessful) {
                result.body()?.let { emit(it) }
            }
        }
    }

    override suspend fun getItemPlayList(id: String): Flow<PagingData<ItemPlayerList>> {
        val loader: ItemPlayListLoader = { pageToken ->
            getItemPlayer(pageToken, id)
        }
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { ItemPlayListPagingSource(loader) }
        ).flow
    }

    private suspend fun getItemPlayer(pageToken: String, id: String): ItemPlayList =
        withContext(Dispatchers.IO) {
            val result = itemApi.getItemPlayList(
                pageToken = pageToken,
                id = id
            )
            return@withContext result.body()!!
        }
}