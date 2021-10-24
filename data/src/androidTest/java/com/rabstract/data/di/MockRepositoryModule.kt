package com.rabstract.data.di

import com.rabstract.data.repository.*
import org.koin.dsl.module

val repositoryAndroidTestModule = module {

    fun provideGifFavouriteRepository(): GifFavouriteRepository {
        return GifFavouriteRepositoryImpl()
    }
    factory { provideGifFavouriteRepository() }
}