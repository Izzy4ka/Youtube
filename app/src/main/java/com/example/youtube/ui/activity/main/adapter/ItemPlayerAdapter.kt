package com.example.youtube.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.R
import com.example.youtube.databinding.ItemPlaylistsBinding
import com.example.youtube.domain.play_list.entity.ItemPlayer
import com.example.youtube.ui.utils.ext.loadImage

class ItemPlayerAdapter(private val result: ItemPlayerCallBack.Result) :
    PagingDataAdapter<ItemPlayer, ItemPlayerAdapter.ItemPlayerViewHolder>(ItemPlayerCallBack()) {
    override fun onBindViewHolder(holder: ItemPlayerViewHolder, position: Int) {
        holder.onBind(position)
        holder.initBtn(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPlayerViewHolder {
        val binding = ItemPlaylistsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemPlayerViewHolder(binding)
    }

    inner class ItemPlayerViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = getItem(position) ?: return
            val count = itemView.context.getString(
                R.string.video_series,
                item.contentDetails?.itemCount.toString()
            )
            with(binding) {
                item.snippet?.thumb?.maxres?.url?.let { itemImagePlay.loadImage(it) }
                itemTxtTitle.text = item.snippet?.title
                itemTxtCount.text = count
                itemTxtPlaylist.text = item.snippet?.channelTitle
            }
        }

        fun initBtn(position: Int) {
            val item = getItem(position) ?: return
            binding.root.setOnClickListener { result.click(item.id!!) }
        }
    }
}

class ItemPlayerCallBack : DiffUtil.ItemCallback<ItemPlayer>() {
    override fun areItemsTheSame(oldItem: ItemPlayer, newItem: ItemPlayer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemPlayer, newItem: ItemPlayer): Boolean {
        return oldItem == newItem
    }

    interface Result {
        fun click(id: String)
    }
}