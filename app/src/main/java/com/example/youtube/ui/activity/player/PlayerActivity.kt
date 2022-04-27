package com.example.youtube.ui.activity.player

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.youtube.R
import com.example.youtube.base.BaseActivity
import com.example.youtube.databinding.ActivityPlayerBinding
import com.example.youtube.domain.item_play_list.entity.ItemPlayerList
import com.example.youtube.domain.play_list.entity.PlayList
import com.example.youtube.ui.activity.main.adapter.DefaultLoadStateAdapter
import com.example.youtube.ui.activity.main.adapter.TryAgainAction
import com.example.youtube.ui.activity.player.adapter.PlayerItemAdapter
import com.example.youtube.ui.utils.const.ID_KEY
import com.example.youtube.ui.utils.ext.gone
import com.example.youtube.ui.utils.ext.isOnline
import com.example.youtube.ui.utils.ext.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : BaseActivity<PlayerViewModel, ActivityPlayerBinding>(
    ActivityPlayerBinding::inflate
) {
    override val viewModel by viewModel<PlayerViewModel>()

    private lateinit var adapter: PlayerItemAdapter

    private var id = ""

    override fun setupUI() {
        initAdapter()
        initToolbar()
        initBtn()
    }

    override fun setupObservers() {
        super.setupObservers()
        observeData()
        observePlayList()
        observeItemPlayList()
        observeState()
    }

    private fun observePlayList() {
        viewModel.getPlayList(id)
        viewModel.playList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handlePlayList(it) }.launchIn(lifecycleScope)
    }

    private fun handlePlayList(it: PlayList) {
        val count = getString(
            R.string.video_series,
            it.items?.get(0)?.contentDetails?.itemCount.toString()
        )
        with(binding) {
            txtMainTitle.text = it.items?.get(0)?.snippet?.title
            txtMainDescription.text = it.items?.get(0)?.snippet?.description
            txtCountVideo.text = count
        }
    }

    private fun observeData() {
        val date: Bundle? = intent.extras
        id = date?.getString(ID_KEY)!!
    }

    private fun observeItemPlayList() {
        lifecycleScope.launch {
            viewModel.getItemPlayList(id).flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .onEach { handleItemPlayList(it) }.launchIn(lifecycleScope)
        }
    }

    private suspend fun handleItemPlayList(it: PagingData<ItemPlayerList>) {
        adapter.submitData(it)
    }

    private fun observeState() {
        adapter.loadStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).onEach {
            handleState(it)
        }.launchIn(lifecycleScope)
    }

    private fun handleState(state: CombinedLoadStates) {
        binding.progressMain.isVisible = state.refresh is LoadState.Loading
        binding.checkInternet.root.isVisible = state.refresh is LoadState.Error
        binding.btnPlay.isVisible = state.refresh is LoadState.Error
    }

    private fun initAdapter() {
        adapter = PlayerItemAdapter()

        val tryAgainAction: TryAgainAction = { adapter.retry() }

        val loadAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoad = adapter.withLoadStateFooter(loadAdapter)

        binding.rvPlayer.adapter = adapterWithLoad
    }

    override fun checkInternet() {
        super.checkInternet()
        if (isOnline()) {
            binding.checkInternet.root.gone()
            binding.btnPlay.gone()
        } else {
            binding.checkInternet.root.visible()
            binding.btnPlay.visible()
        }
    }

    private fun initBtn() {
        binding.txtBack.setOnClickListener {
            finish()
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }

}