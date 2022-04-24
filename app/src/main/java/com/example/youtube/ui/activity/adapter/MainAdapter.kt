package com.example.youtube.ui.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.databinding.ItemPlaylistsBinding
import com.example.youtube.domain.play_list.entity.ItemPlayer

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private val list: List<ItemPlayer> get() = temp
    var temp: List<ItemPlayer> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MainViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val count = "${list[position].contentDetails?.itemCount} video series"
            Glide.with(binding.itemImagePlay).load(list[position].snippet?.thumb?.maxres?.url)
                .into(binding.itemImagePlay)
            binding.itemTxtTitle.text = list[position].snippet?.title
            binding.itemTxtCount.text = count
            binding.itemTxtPlaylist.text = list[position].snippet?.channelTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemPlaylistsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = list.size
}