package com.rabstract.domain.di

import com.rabstract.data.di.dataModule
import com.rabstract.domain.usecase.favourite.GetFavoritesGifsUseCase
import com.rabstract.domain.usecase.trending.GetTrendingGifsUseCase
import com.rabstract.domain.usecase.trending.RemoveTrendingGifUseCase
import com.rabstract.domain.usecase.trending.SaveTrendingGifUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val domainModule = module {
    factory { GetFavoritesGifsUseCase(repository = get()) }
    factory { GetTrendingGifsUseCase(gifTrendingRepository = get(),gifFavouriteRepository = get()) }
    factory { RemoveTrendingGifUseCase(gifFavouriteRepository = get()) }
    factory { SaveTrendingGifUseCase(gifFavouriteRepository = get()) }

    loadKoinModules(dataModule)
}
