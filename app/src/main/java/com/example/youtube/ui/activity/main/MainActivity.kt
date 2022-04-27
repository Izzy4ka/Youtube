package com.example.youtube.ui.activity.main

import android.content.Intent
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.domain.play_list.entity.ItemPlayer
import com.example.youtube.base.BaseActivity
import com.example.youtube.ui.activity.main.adapter.DefaultLoadStateAdapter
import com.example.youtube.ui.activity.main.adapter.ItemPlayerAdapter
import com.example.youtube.ui.activity.main.adapter.ItemPlayerCallBack
import com.example.youtube.ui.activity.main.adapter.TryAgainAction
import com.example.youtube.ui.activity.player.PlayerActivity
import com.example.youtube.ui.utils.const.ID_KEY
import com.example.youtube.ui.utils.ext.gone
import com.example.youtube.ui.utils.ext.isOnline
import com.example.youtube.ui.utils.ext.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    ActivityMainBinding::inflate
), ItemPlayerCallBack.Result {
    override val viewModel by viewModel<MainViewModel>()

    private lateinit var adapter: ItemPlayerAdapter

    override fun setupUI() {
        initBtn()
        initAdapter()
    }

    private fun initBtn() {
        binding.checkInternet.btnTryAgain.setOnClickListener {
            checkInternet()
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        observeState()
        observePlayList()
    }

    private fun initAdapter() {
        adapter = ItemPlayerAdapter(this)

        val tryAgainAction: TryAgainAction = { adapter.retry() }

        val loadAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoad = adapter.withLoadStateFooter(loadAdapter)

        binding.rvMain.adapter = adapterWithLoad
    }

    override fun checkInternet() {
        super.checkInternet()
        if (isOnline())
            binding.checkInternet.root.gone()
        else
            binding.checkInternet.root.visible()

    }

    private fun observePlayList() {
        lifecycleScope.launch {
            viewModel.getPlayList().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .onEach { handlePlayList(it) }.launchIn(lifecycleScope)
        }
    }


    private fun observeState() {
        adapter.loadStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach {
            handleState(it)
        }.launchIn(lifecycleScope)
    }

    private fun handleState(state: CombinedLoadStates) {
        binding.progressMain.isVisible = state.refresh is LoadState.Loading
        binding.checkInternet.root.isVisible = state.refresh is LoadState.Error
    }


    private suspend fun handlePlayList(item: PagingData<ItemPlayer>) {
        adapter.submitData(item)
    }

    override fun click(id: String) {
        putData(id)
    }

    private fun putData(id: String) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra(ID_KEY, id)
        startActivity(intent)
    }

}