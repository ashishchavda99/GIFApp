package com.rabstract.data.db.dao

import androidx.room.*
import com.rabstract.model.FavouriteGif
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteGifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favouriteGif: FavouriteGif)

    @Delete
    suspend fun delete(favouriteGif: FavouriteGif): Int

    @Query("SELECT * FROM my_fav_gif")
    fun getFavouriteGifs(): Flow<List<FavouriteGif>>

    @Query("SELECT EXISTS(SELECT * FROM my_fav_gif where id = :id)")
    suspend fun isFavourite(id: String): Boolean

}