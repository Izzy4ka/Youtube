package com.example.youtube.data.item_play_list.remote

import com.example.youtube.BuildConfig
import com.example.youtube.domain.item_play_list.entity.ItemPlayList
import com.example.youtube.domain.play_list.entity.PlayList
import com.example.youtube.ui.utils.const.EMPTY
import com.example.youtube.ui.utils.const.PART
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemPlayListApi {
    @GET("playlists")
    suspend fun getPlayList(
        @Query("part") part: String = PART,
        @Query("id") id: String,
        @Query("key") key: String = BuildConfig.API_KEY,
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getItemPlayList(
        @Query("part") part: String = PART,
        @Query("playlistId") id: String,
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("pageToken") pageToken: String = EMPTY
    ): Response<ItemPlayList>
}