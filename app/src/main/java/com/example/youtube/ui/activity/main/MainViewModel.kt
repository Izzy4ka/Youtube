package com.example.youtube.ui.activity.main

import androidx.paging.PagingData
import com.example.youtube.base.BaseViewModel
import com.example.youtube.domain.play_list.entity.ItemPlayer
import com.example.youtube.domain.play_list.use_case.GetPlayListUseCase
import kotlinx.coroutines.flow.*

class MainViewModel(
    private val getPlayListUseCase: GetPlayListUseCase
) : BaseViewModel() {



     suspend fun getPlayList(): Flow<PagingData<ItemPlayer>> =
        getPlayListUseCase.getPlayList()
}



