package com.rabstract.data.base

import com.rabstract.data.db.GifDataBase
import com.rabstract.data.db.dao.FavouriteGifDao
import com.rabstract.model.Downsized
import com.rabstract.model.FavouriteGif
import com.rabstract.model.GifData
import com.rabstract.model.Images
import org.junit.After
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Creates base
 */
abstract class BaseDBTest : KoinTest {

    val databse: GifDataBase by inject()
    val favouriteGifDao: FavouriteGifDao by inject()


    @After
    open fun tearDown(){
        //Stop Koin as well
        stopKoin()
    }

    fun getFavouriteGifData(id: String = "testId"): FavouriteGif {

        return FavouriteGif(id, getGifData(id))
    }

    fun getGifData(id: String = "testId"): GifData {

        val downloadSize = Downsized(100, 100, 100, "https://test.com")

        val images = Images(downloadSize, downloadSize, downloadSize, downloadSize, downloadSize)

        return GifData(id, images, true)

    }
}