package com.example.youtube.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.domain.play_list.entity.ItemPlayer
import com.example.youtube.ui.activity.adapter.MainAdapter
import com.example.youtube.ui.base.BaseActivity
import com.example.youtube.utils.ext.gone
import com.example.youtube.utils.ext.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var adapter: MainAdapter

    override fun setupUi() {
        initBtn()
        initAdapter()
    }

    private fun initBtn() {
        binding.checkInternet.btnTryAgain.setOnClickListener {
            checkInternet()
        }
    }

    private fun initAdapter() {
        adapter = MainAdapter()
        binding.rvMain.adapter = adapter
    }

    override fun checkInternet() {
        super.checkInternet()
        if (isOnline(this)) {
            binding.checkInternet.root.gone()
            viewModel.fetchPlayList()
            observePlayList()
        } else
            binding.checkInternet.root.visible()
    }

    private fun observePlayList() {
        viewModel.playList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { it.items?.let { its -> handlePlayList(its) } }.launchIn(lifecycleScope)
    }

    private fun handlePlayList(it: List<ItemPlayer>) {
        adapter.temp = it
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}