package com.example.youtube.ui.utils.di

import com.example.youtube.ui.activity.main.MainViewModel
import com.example.youtube.ui.activity.player.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModule = module {
    viewModel {
        MainViewModel(getPlayListUseCase = get())
    }
    viewModel {
        PlayerViewModel(
            getItemPlayListUseCase = get(),
            getOnePlayListUseCase = get()
        )
    }
}