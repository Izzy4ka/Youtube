package com.example.youtube.domain.item_play_list.repository

import androidx.paging.PagingData
import com.example.youtube.domain.item_play_list.entity.ItemPlayerList
import com.example.youtube.domain.play_list.entity.ItemPlayer
import com.example.youtube.domain.play_list.entity.PlayList
import kotlinx.coroutines.flow.Flow

interface ItemPlayerRepository {
    suspend fun getPlayList(id: String): Flow<PlayList>
    suspend fun getItemPlayList(id: String): Flow<PagingData<ItemPlayerList>>
}