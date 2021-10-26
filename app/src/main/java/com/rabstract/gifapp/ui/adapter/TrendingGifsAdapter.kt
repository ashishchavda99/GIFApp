package com.rabstract.gifapp.ui.adapter

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rabstract.gifapp.R
import com.rabstract.gifapp.extensions.inflate
import com.rabstract.model.GifData
import kotlinx.android.synthetic.main.item_list_gif.view.*

class TrendingGifsAdapter(private val favoriteGifListener: (GifData) -> Unit) : PagingDataAdapter<GifData, TrendingGifViewHolder>(GifDiffCallback)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingGifViewHolder {
        val view = parent.inflate(R.layout.item_list_gif)
        val viewHolder = TrendingGifViewHolder(view)
        avoidMultipleClicks(view)
        return viewHolder
    }

    private fun setFavoriteGifListener(trendingGifViewHolder: TrendingGifViewHolder, gifData: GifData?) {
        trendingGifViewHolder.itemView.item_list_gif_favorite_layer.setOnClickListener {
            val position = trendingGifViewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val gifData = gifData?.apply {
                    isFavourite = !isFavourite
                }
                gifData?.let { it1 -> trendingGifViewHolder.animateFavoriteIcon(it1.isFavourite) }
                gifData?.let { it1 -> favoriteGifListener.invoke(it1) }
            }
        }
    }

    private fun avoidMultipleClicks(view: View) {
        view.isClickable = false
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            view.isClickable = true
        }, 1000)
    }
    override fun onBindViewHolder(trendingGifViewHolder: TrendingGifViewHolder, position: Int) {
        val gifData = getItem(position)
        trendingGifViewHolder.apply {
            gifData?.let { populateViews(it) }
        }
        setFavoriteGifListener(trendingGifViewHolder,gifData)
    }
}
