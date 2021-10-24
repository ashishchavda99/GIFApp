package com.rabstract.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rabstract.data.base.BaseDBTest
import com.rabstract.data.di.configureAndroidTestAppComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


@RunWith(AndroidJUnit4::class)
class FavouriteGifRepositoryTest : BaseDBTest() {

    private lateinit var mRepo: GifFavouriteRepository

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

        mRepo = GifFavouriteRepositoryImpl()
        mRepo.saveFavouriteGif(getGifData())
        val gif = mRepo.getFavoriteGifs().first()[0]
        assertEquals(getGifData().id, gif.id)
    }

    @Test
    fun checkIfInsertedDataIsMarkAsFavouriteGotAsFavouriteIfBothDataSame() = runBlocking {
        mRepo = GifFavouriteRepositoryImpl()
        mRepo.saveFavouriteGif(getGifData())
        val isFavourite = mRepo.isFavouriteGif("testId")
        assertTrue(isFavourite)
    }

    @Test
    fun checkIfInsertedDataIsMarkAsFavouriteGotAsFavouriteIfBothDataDifference() = runBlocking {
        mRepo = GifFavouriteRepositoryImpl()
        mRepo.saveFavouriteGif(getGifData())
        val isFavourite = mRepo.isFavouriteGif("newId")
        assertFalse(isFavourite)
    }

    @Test
    fun checkIfDeleteFavouriteGotRawAffectedMoreThanZeroIfIDFound() = runBlocking {
        mRepo = GifFavouriteRepositoryImpl()
        mRepo.saveFavouriteGif(getGifData())
        val rowDeleted = mRepo.deleteFavoriteGif(getGifData())
        assertTrue(rowDeleted > 0)
    }

    @Test
    fun checkIfDeleteFavouriteGotRawAffectedMoreThanZeroIfIDNotFound() = runBlocking {
        mRepo = GifFavouriteRepositoryImpl()
        mRepo.saveFavouriteGif(getGifData())
        val rowDeleted = mRepo.deleteFavoriteGif(getGifData("newId"))
        assertFalse(rowDeleted > 0)

    }

}
