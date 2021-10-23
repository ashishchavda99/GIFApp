package com.rabstract.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rabstract.data.db.converter.FavGifJsonConverter
import com.rabstract.data.db.dao.FavouriteGifDao
import com.rabstract.data.db.entity.FavouriteGif

@Database(
    entities = [
        FavouriteGif::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(FavGifJsonConverter::class)
abstract class GifDataBase : RoomDatabase() {

    abstract fun getFavouriteGifDao(): FavouriteGifDao

}