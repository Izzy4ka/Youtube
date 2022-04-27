package com.example.youtube.data.playlists.remote

import com.example.youtube.BuildConfig.API_KEY
import com.example.youtube.domain.play_list.entity.PlayList
import com.example.youtube.ui.utils.const.CHANNEL_ID
import com.example.youtube.ui.utils.const.EMPTY
import com.example.youtube.ui.utils.const.MAX_RESULT
import com.example.youtube.ui.utils.const.PART
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiPlayList {

    @GET("playlists")
    suspend fun getPlayList(
        @Query("part") part: String = PART,
        @Query("channelId") channelId: String = CHANNEL_ID,
        @Query("key") key: String = API_KEY,
        @Query("maxResults") max: Int = MAX_RESULT,
        @Query("pageToken") pageToken: String = EMPTY
    ): Response<PlayList>
}