package com.example.youtube.ui.activity.player

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.youtube.base.BaseViewModel
import com.example.youtube.domain.item_play_list.entity.ItemPlayerList
import com.example.youtube.domain.item_play_list.use_case.GetItemPlayListUseCase
import com.example.youtube.domain.item_play_list.use_case.GetOnePlayListUseCase
import com.example.youtube.domain.play_list.entity.PlayList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val getItemPlayListUseCase: GetItemPlayListUseCase,
    private val getOnePlayListUseCase: GetOnePlayListUseCase
) : BaseViewModel() {

    private var _playList = MutableStateFlow(PlayList())
    val playList: StateFlow<PlayList> get() = _playList


    suspend fun getItemPlayList(id: String): Flow<PagingData<ItemPlayerList>> =
        getItemPlayListUseCase.getItemPlayList(id)

    fun getPlayList(id: String) {
        viewModelScope.launch {
            getOnePlayListUseCase.getPlayList(id)
                .collect {
                    _playList.value = it
                }
        }
    }
}