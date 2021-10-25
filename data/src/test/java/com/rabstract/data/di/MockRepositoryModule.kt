package com.rabstract.data.di

import com.rabstract.data.GifAppApi
import com.rabstract.data.repository.*
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryTestModule = module {

    fun provideGifFavouriteRepository(): GifFavouriteRepository {
        return GifFavouriteRepositoryImpl()
    }
    factory { provideGifFavouriteRepository() }

    single { GifTrendingRepository(api = get(), favouriteRepository = get()) }

}