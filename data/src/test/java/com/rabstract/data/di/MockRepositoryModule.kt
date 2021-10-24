package com.rabstract.data.di

import com.rabstract.data.repository.*
import org.koin.dsl.module

val repositoryTestModule = module {

    fun provideGifTrendingRepository(): GifTrendingRepository {
        return GifTrendingRepositoryImpl()
    }
    factory { provideGifTrendingRepository() }
}