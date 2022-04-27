package com.example.youtube.data.item_play_list.module

import com.example.youtube.data.item_play_list.remote.ItemPlayListApi
import com.example.youtube.data.item_play_list.repo.ItemPlayerRepositoryImpl
import com.example.youtube.domain.item_play_list.repository.ItemPlayerRepository
import com.example.youtube.domain.item_play_list.use_case.GetItemPlayListUseCase
import com.example.youtube.domain.item_play_list.use_case.GetOnePlayListUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val itemPlayListModule = module {
    single<ItemPlayListApi> { get<Retrofit>().create(ItemPlayListApi::class.java) }
    factory<ItemPlayerRepository> { ItemPlayerRepositoryImpl(get()) }
    factory { GetItemPlayListUseCase(get()) }
    factory { GetOnePlayListUseCase(get()) }
}