package com.example.youtube.domain.item_play_list.use_case

import com.example.youtube.domain.item_play_list.repository.ItemPlayerRepository
import com.example.youtube.domain.play_list.entity.PlayList
import kotlinx.coroutines.flow.Flow

class GetOnePlayListUseCase(
    private val repository: ItemPlayerRepository
) {
    suspend fun getPlayList(id: String): Flow<PlayList> = repository.getPlayList(id)
}