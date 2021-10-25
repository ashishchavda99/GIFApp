package com.rabstract.gifapp.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rabstract.gifapp.R
import com.rabstract.gifapp.extensions.inflate
import com.rabstract.model.FavouriteGif
import com.rabstract.model.GifData
import kotlinx.android.synthetic.main.item_list_gif.view.*

class FavoriteGifAdapter(private val favoriteGifListener: (FavouriteGif) -> Unit) : RecyclerView.Adapter<FavoriteViewHolder>() {

    private lateinit var favouriteGifs: List<FavouriteGif>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = parent.inflate(R.layout.item_list_gif)
        val viewHolder = FavoriteViewHolder(view)
        setFavoriteIconListener(viewHolder)
        return viewHolder
    }

    private fun setFavoriteIconListener(viewHolder: FavoriteViewHolder) {
        viewHolder.itemView.item_list_gif_favorite_layer.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                favoriteGifListener.invoke(favouriteGifs[position])
            }
        }
    }

    override fun getItemCount(): Int = favouriteGifs.size

    override fun onBindViewHolder(viewHolder: FavoriteViewHolder, position: Int) {
        viewHolder.populateViews(favouriteGifs[position])
    }

    fun setData(favouriteGifs: List<FavouriteGif>) {
        this.favouriteGifs = favouriteGifs
    }
}
