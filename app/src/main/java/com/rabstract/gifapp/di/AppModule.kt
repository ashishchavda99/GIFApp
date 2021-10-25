package com.rabstract.gifapp.di

import com.rabstract.domain.di.domainModule
import com.rabstract.gifapp.ui.adapter.FavoriteGifAdapter
import com.rabstract.gifapp.ui.adapter.TrendingGifsAdapter
import com.rabstract.gifapp.ui.main.CommonViewModel
import com.rabstract.gifapp.ui.main.FavouriteViewModel
import com.rabstract.gifapp.ui.main.TrendingGifViewModel
import com.rabstract.model.FavouriteGif
import com.rabstract.model.GifData
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val appModule = module {
    viewModel {
        TrendingGifViewModel(getTrendingGifsUseCase = get(),
            removeTrendingGifUseCase = get(),
            saveTrendingGifUseCase = get()
        )
    }
    viewModel {
        FavouriteViewModel(getFavoritesGifsUseCase = get(),
            removeTrendingGifUseCase = get(),
            saveTrendingGifUseCase = get()
        )
    }
    viewModel {
        CommonViewModel()
    }

    factory { (favoriteGifListener: (GifData) -> Unit) ->
        TrendingGifsAdapter(favoriteGifListener = favoriteGifListener)
    }
    factory { (favoriteGifListener: (FavouriteGif) -> Unit) ->
        FavoriteGifAdapter(favoriteGifListener = favoriteGifListener)
    }
    loadKoinModules(domainModule)
}
