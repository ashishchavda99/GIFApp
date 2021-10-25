package com.rabstract.gifapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rabstract.gifapp.R
import com.rabstract.gifapp.extensions.loadImage
import com.rabstract.model.FavouriteGif
import kotlinx.android.synthetic.main.item_list_gif.view.*

class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateViews(favouriteGif: FavouriteGif) {
        itemView.item_list_gif_image.loadImage(favouriteGif.data.images.downsizedMedium.url)
        itemView.item_list_beer_favorite_button.background =
                itemView.context.getDrawable(R.drawable.ic_star_white_24dp)
    }
}
