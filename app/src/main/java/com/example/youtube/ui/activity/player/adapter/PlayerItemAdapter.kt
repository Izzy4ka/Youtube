package com.example.youtube.ui.activity.player.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.databinding.ItemPlayerBinding
import com.example.youtube.domain.item_play_list.entity.ItemPlayerList
import com.example.youtube.ui.utils.ext.loadImage

class PlayerItemAdapter : PagingDataAdapter<ItemPlayerList, PlayerItemAdapter.ItemHolder>(ItemPlayerCallBack()) {
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBind(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemHolder(binding)
    }

    inner class ItemHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = getItem(position) ?: return
            with(binding) {
                item.snippet?.thumb?.maxres?.url?.let { itemImagePlay.loadImage(it) }
                binding.itemTxtTitle.text = item.snippet?.title
                binding.itemTxtCount.text = item.contentDetails?.videoPublishedAt
            }
        }
    }
}
class ItemPlayerCallBack : DiffUtil.ItemCallback<ItemPlayerList>() {
    override fun areItemsTheSame(oldItem: ItemPlayerList, newItem: ItemPlayerList): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemPlayerList, newItem: ItemPlayerList): Boolean {
        return oldItem == newItem
    }
}