package com.example.youtube.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemLoadStateBinding

typealias  TryAgainAction = () -> Unit

class DefaultLoadStateAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<DefaultLoadStateAdapter.Holder>() {
    class Holder(
        private val binding: ItemLoadStateBinding,
        private val action: TryAgainAction
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(loadState: LoadState) {
            with(binding) {
                itemTxtMessage.isVisible = loadState is LoadState.Error
                itemProgress.isVisible = loadState is LoadState.Loading
                itemBtnTryAgain.isVisible = loadState is LoadState.Error
            }
        }

        fun initBtn() {
            binding.itemBtnTryAgain.setOnClickListener { action() }
        }

    }

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.onBind(loadState)
        holder.initBtn()
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val binding = ItemLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return Holder(binding, tryAgainAction)
    }
}