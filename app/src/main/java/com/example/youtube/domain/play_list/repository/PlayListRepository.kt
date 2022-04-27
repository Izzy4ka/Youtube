package com.example.youtube.domain.play_list.repository

import androidx.paging.PagingData
import com.example.youtube.domain.play_list.entity.ItemPlayer
import kotlinx.coroutines.flow.Flow

interface PlayListRepository {
    suspend fun getPlayList(): Flow<PagingData<ItemPlayer>>
}