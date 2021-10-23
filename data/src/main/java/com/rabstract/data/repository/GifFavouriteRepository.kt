package com.rabstract.data.repository

import com.rabstract.data.db.entity.FavouriteGif
import com.rabstract.model.GifData
import kotlinx.coroutines.flow.Flow


interface GifFavouriteRepository {

    suspend fun saveFavouriteGif(gifData: GifData)
    suspend fun isFavouriteGif(id: String)
    suspend fun deleteFavoriteGif(gifData: GifData)
    suspend fun getFavoriteGifs() : Flow<List<FavouriteGif>>
}