package com.rabstract.data.repository

import com.rabstract.model.FavouriteGif
import com.rabstract.model.GifData
import kotlinx.coroutines.flow.Flow


interface GifFavouriteRepository {

    suspend fun saveFavouriteGif(gifData: GifData)
    suspend fun isFavouriteGif(id: String): Boolean
    suspend fun deleteFavoriteGif(gifData: GifData): Int
    fun getFavoriteGifs() : Flow<List<FavouriteGif>>
}