package com.example.youtube.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    private val _state =
        MutableStateFlow<BaseActivityState>(BaseActivityState.Init)
    val state: StateFlow<BaseActivityState> get() = _state


    protected fun showMessage(message: String?) {
        _state.value = BaseActivityState.ShowMessage(message)
    }

    protected fun hideLoading() {
        _state.value = BaseActivityState.IsLoading(false)
    }

    protected fun setLoading() {
        _state.value = BaseActivityState.IsLoading(true)
    }

    sealed class BaseActivityState {
        object Init : BaseActivityState()
        data class IsLoading(val isLoading: Boolean) : BaseActivityState()
        data class ShowMessage(val message: String?) : BaseActivityState()
    }
}