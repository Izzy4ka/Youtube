package com.example.youtube

import android.app.Application
import com.example.youtube.data.common.module.networkModule
import com.example.youtube.data.item_play_list.module.itemPlayListModule
import com.example.youtube.data.playlists.module.playListModule
import com.example.youtube.ui.utils.di.viewModuleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(networkModule, viewModuleModule, playListModule, itemPlayListModule))
        }
    }
}