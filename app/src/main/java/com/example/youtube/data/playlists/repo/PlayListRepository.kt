package com.example.youtube.data.playlists.repo

import com.example.youtube.data.common.RetrofitClient
import com.example.youtube.domain.play_list.entity.PlayList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayListRepository {
    companion object {
        suspend fun getPlayList(): Flow<PlayList> {
            return flow {
                val result = RetrofitClient.create().getPlayList()
                if (result.isSuccessful) {
                    val body = result.body()
                    body?.let { emit(it) }
                }
            }
        }
    }
}