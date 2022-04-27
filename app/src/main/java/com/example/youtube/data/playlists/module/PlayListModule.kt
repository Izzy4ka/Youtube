package com.example.youtube.data.playlists.module

import com.example.youtube.data.playlists.remote.ApiPlayList
import com.example.youtube.data.playlists.repo.PlayListRepositoryImpl
import com.example.youtube.domain.item_play_list.use_case.GetItemPlayListUseCase
import com.example.youtube.domain.play_list.repository.PlayListRepository
import com.example.youtube.domain.play_list.use_case.GetPlayListUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val playListModule = module {
    single<ApiPlayList> { get<Retrofit>().create(ApiPlayList::class.java) }
    factory<PlayListRepository> { PlayListRepositoryImpl(get()) }
    factory { GetPlayListUseCase(get()) }
}