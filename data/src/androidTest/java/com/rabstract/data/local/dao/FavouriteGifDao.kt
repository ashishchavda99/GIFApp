package com.rabstract.data.local.dao

import androidx.room.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rabstract.data.base.BaseDBTest
import com.rabstract.data.di.configureAndroidTestAppComponent
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class FavouriteGifDaoTest : BaseDBTest() {


    @Before
    fun start() {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(configureAndroidTestAppComponent())
        }
    }
    @After
    fun after() {
        databse.close()
        stopKoin()
    }
    @Test
    fun checkInsertFavouriteGettingAsSameResponse() = runBlocking {

        val favouriteGifInserted = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGifInserted)
        val favouritesGifs = favouriteGifDao.getFavouriteGifs().first()
        val gif = favouritesGifs[0]
        assertEquals(favouriteGifInserted.id, gif.id)
    }

    @Test
    fun checkIfInsertedDataIsMarkAsFavouriteGotAsFavouriteIfBothDataSame() = runBlocking {
        val favouriteGifInserted = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGifInserted)
        val isFavourite = favouriteGifDao.isFavourite("testId")
        Assert.assertTrue(isFavourite)
    }

    @Test
    fun checkIfInsertedDataIsMarkAsFavouriteGotAsFavouriteIfBothDataDifference() = runBlocking {
        val favouriteGifInserted = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGifInserted)
        val isFavourite = favouriteGifDao.isFavourite("newId")
        Assert.assertFalse(isFavourite)
    }

    @Test
    fun checkIfDeleteFavouriteGotRawAffectedMoreThanZeroIfIDFound() = runBlocking {
        val favouriteGifInserted = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGifInserted)
        val rowDeleted = favouriteGifDao.delete(favouriteGifInserted)
        Assert.assertTrue(rowDeleted > 0)
    }

    @Test
    fun checkIfDeleteFavouriteGotRawAffectedMoreThanZeroIfIDNotFound() = runBlocking {
        val favouriteGifInserted = getFavouriteGifData()
        favouriteGifDao.insert(favouriteGifInserted)
        val rowDeleted = favouriteGifDao.delete(getFavouriteGifData("newId"))
        Assert.assertFalse(rowDeleted > 0)

    }

}