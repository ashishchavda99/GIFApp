package com.rabstract.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_fav_gif")
data class FavouriteGif(

    @PrimaryKey
    val id: String,

    val data: GifData

)
