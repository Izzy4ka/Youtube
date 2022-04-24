package com.example.youtube.ui.activity

import androidx.lifecycle.viewModelScope
import com.example.youtube.data.playlists.repo.PlayListRepository
import com.example.youtube.domain.play_list.entity.PlayList
import com.example.youtube.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    private val _playList = MutableStateFlow(PlayList())
    val playList: StateFlow<PlayList> get() = _playList




     fun fetchPlayList() {
        viewModelScope.launch {
            PlayListRepository.getPlayList()
                .collect {
                    _playList.value = it
                }
        }
    }
}
