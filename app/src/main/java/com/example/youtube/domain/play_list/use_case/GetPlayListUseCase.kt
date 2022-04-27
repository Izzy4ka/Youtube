package com.example.youtube.domain.play_list.use_case

import androidx.paging.PagingData
import com.example.youtube.domain.play_list.entity.ItemPlayer
import com.example.youtube.domain.play_list.repository.PlayListRepository
import kotlinx.coroutines.flow.Flow

class GetPlayListUseCase(private val playListRepository: PlayListRepository) {
    suspend fun getPlayList(): Flow<PagingData<ItemPlayer>> = playListRepository.getPlayList()
}