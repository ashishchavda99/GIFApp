package com.rabstract.gifapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabstract.domain.usecase.favourite.GetFavoritesGifsUseCase
import com.rabstract.domain.usecase.trending.RemoveTrendingGifUseCase
import com.rabstract.domain.usecase.trending.SaveTrendingGifUseCase
import com.rabstract.model.FavouriteGif
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteViewModel(
    private val getFavoritesGifsUseCase: GetFavoritesGifsUseCase,
    private val removeTrendingGifUseCase: RemoveTrendingGifUseCase,
    private val saveTrendingGifUseCase: SaveTrendingGifUseCase
) : ViewModel() {

    private var favouriteGif: FavouriteGif? = null
    fun handleRemoveButton(cachedData: FavouriteGif) = viewModelScope.launch {
        favouriteGif = cachedData
        removeTrendingGifUseCase.execute(cachedData.data)
    }

    fun handleUndoButton() {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteGif?.let { saveTrendingGifUseCase.execute(it.data) }
            withContext(Dispatchers.Main) {
                resetBeerRemovedData()
            }
        }
    }

    private fun resetBeerRemovedData() {
        favouriteGif = null
    }

    val favouriteGifs = getFavoritesGifsUseCase.execute().map { favGifs ->
        return@map favGifs
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(10000),
        initialValue = emptyList()
    )
}