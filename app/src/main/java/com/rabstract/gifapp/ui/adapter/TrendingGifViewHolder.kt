package com.rabstract.gifapp.ui.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.recyclerview.widget.RecyclerView
import com.rabstract.gifapp.R
import com.rabstract.gifapp.extensions.loadImage
import com.rabstract.model.GifData
import kotlinx.android.synthetic.main.item_list_gif.view.*

class TrendingGifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateViews(gifData: GifData) {
        itemView.item_list_gif_image.loadImage(gifData.images.downsizedMedium.url)
        populateFavoriteIconView(gifData.isFavourite)
    }

    fun animateFavoriteIcon(isFavoriteGif: Boolean) {
        val viewPropertyAnimator = itemView.item_list_beer_favorite_button.animate()
        viewPropertyAnimator
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setDuration(250)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        populateFavoriteIconView(isFavoriteGif)
                        restartFavoriteIconSize(viewPropertyAnimator)
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        super.onAnimationStart(animation)
                        itemView.isClickable = false
                    }
                })
    }

    private fun populateFavoriteIconView(isFavorite: Boolean) {
        getFavoriteIcon(isFavorite)?.let {
            itemView.item_list_beer_favorite_button.background = it
        }
    }

    private fun getFavoriteIcon(isFavorite: Boolean): Drawable? {
        return if (isFavorite) {
            itemView.context.getDrawable(R.drawable.ic_star_white_24dp)
        } else {
            itemView.context.getDrawable(R.drawable.ic_star_border_white_24dp)
        }
    }

    private fun restartFavoriteIconSize(viewPropertyAnimator: ViewPropertyAnimator) {
        viewPropertyAnimator
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(250)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        viewPropertyAnimator.cancel()
                        itemView.isClickable = true
                    }
                })
    }
}
