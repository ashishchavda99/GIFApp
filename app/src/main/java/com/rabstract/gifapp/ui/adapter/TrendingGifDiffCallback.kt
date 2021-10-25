package com.rabstract.gifapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rabstract.model.GifData

object GifDiffCallback : DiffUtil.ItemCallback<GifData>() {

    override fun areItemsTheSame(oldItem: GifData, newItem: GifData): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: GifData, newItem: GifData): Boolean {
        return oldItem.id == newItem.id
    }
}