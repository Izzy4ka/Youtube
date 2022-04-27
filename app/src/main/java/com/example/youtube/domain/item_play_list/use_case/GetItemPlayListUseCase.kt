package com.example.youtube.domain.item_play_list.use_case

import androidx.paging.PagingData
import com.example.youtube.domain.item_play_list.entity.ItemPlayerList
import com.example.youtube.domain.item_play_list.repository.ItemPlayerRepository
import com.example.youtube.domain.play_list.entity.ItemPlayer
import kotlinx.coroutines.flow.Flow

class GetItemPlayListUseCase(private val repository: ItemPlayerRepository) {
    suspend fun getItemPlayList(id: String): Flow<PagingData<ItemPlayerList>> =
        repository.getItemPlayList(id)
}