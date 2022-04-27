package com.example.youtube.domain.item_play_list.entity

import com.example.youtube.domain.play_list.entity.Thumb
import com.google.gson.annotations.SerializedName

data class ItemPlayList(
    val kind: String? = null,
    @SerializedName("etag")
    val tag: String? = null,
    val nextPageToken: String? = null,
    val prevPageToken: String? = null,
    @SerializedName("items")
    val items: List<ItemPlayerList>? = null
)

data class ItemPlayerList(
    val kind: String? = null,
    @SerializedName("etag")
    val tag: String? = null,
    val id: String? = null,
    val snippet: ItemSnippet? = null,
    val contentDetails: ItemContentDetails? = null
)

data class ItemSnippet(
    @SerializedName("publishedAt")
    val publish: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    @SerializedName("thumbnails")
    val thumb: Thumb? = null,
    val channelTitle: String? = null,
    val playlistId: String? = null,
    val position: Int? = null,
    val resourceId: ResourceId? = null,
    val videoOwnerChannelTitle: String? = null,
    val videoOwnerChannelId: String? = null,
)

data class ResourceId(
    val kind: String? = null,
    val videoId: String? = null
)

data class ItemContentDetails(
    val videoId: String? = null,
    val videoPublishedAt: String? = null
)
