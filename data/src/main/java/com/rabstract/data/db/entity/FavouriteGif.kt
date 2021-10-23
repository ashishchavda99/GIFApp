package com.rabstract.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rabstract.model.GifData

@Entity(tableName = "my_fav_gif")
data class FavouriteGif(

    @PrimaryKey
    val id: String,

    val data: GifData

)
