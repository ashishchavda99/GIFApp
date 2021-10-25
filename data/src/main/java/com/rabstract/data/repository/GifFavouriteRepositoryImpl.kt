package com.rabstract.data.repository

import com.rabstract.data.GifAppApi
import com.rabstract.data.db.dao.FavouriteGifDao
import com.rabstract.model.FavouriteGif
import com.rabstract.model.GifData
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class GifFavouriteRepositoryImpl : GifFavouriteRepository, KoinComponent {

    val api: GifAppApi by inject()
    val dao: FavouriteGifDao by inject()

    /**
     * saveGif to DB
     */

    override suspend fun saveFavouriteGif(gifData: GifData) {
        dao.insert(FavouriteGif(gifData.id, gifData))
    }

    /**
     * check selected gif is favourite or not
     */

    override suspend fun isFavouriteGif(id: String): Boolean {
        return dao.isFavourite(id)
    }

    /**
     * deleted selected gif from favourite list
     */

    override suspend fun deleteFavoriteGif(gifData: GifData): Int {
        return dao.delete(FavouriteGif(gifData.id, gifData))
    }

    override fun getFavoriteGifs(): Flow<List<FavouriteGif>> {
        return dao.getFavouriteGifs()
    }

}