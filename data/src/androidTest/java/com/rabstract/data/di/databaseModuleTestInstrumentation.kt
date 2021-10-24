package com.rabstract.data.di

import android.content.Context
import androidx.room.Room
import com.rabstract.data.db.GifDataBase
import com.rabstract.data.db.dao.FavouriteGifDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Context): GifDataBase {
            return Room.inMemoryDatabaseBuilder(application, GifDataBase::class.java)
                .allowMainThreadQueries()
                .build()
        }


    fun provideFavouriteGifDaoDao(database: GifDataBase): FavouriteGifDao {
        return  database.getFavouriteGifDao()
    }

    single { provideDatabase(androidContext()) }
    single { provideFavouriteGifDaoDao(get()) }


}
