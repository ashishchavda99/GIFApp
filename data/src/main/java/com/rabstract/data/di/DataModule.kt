package com.rabstract.data.di

import android.app.Application
import androidx.room.Room
import com.rabstract.data.db.GifDataBase
import com.rabstract.data.db.dao.FavouriteGifDao
import com.rabstract.data.repository.GifRepository
import com.rabstract.data.repository.GifRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    fun provideDatabase(application: Application): GifDataBase {
        return Room.databaseBuilder(application, GifDataBase::class.java, "gifdata")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { provideDatabase(androidApplication()) }
    fun provideFavouriteGifDao(database: GifDataBase): FavouriteGifDao {
        return database.getFavouriteGifDao()
    }

    single { provideFavouriteGifDao(get()) }


    fun provideGifRepository(): GifRepository {
        return GifRepositoryImpl()
    }
    factory { provideGifRepository() }

}






