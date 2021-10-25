package com.rabstract.domain.usecase.favourite

import com.rabstract.data.repository.GifFavouriteRepository
import com.rabstract.model.FavouriteGif
import kotlinx.coroutines.flow.Flow

class GetFavoritesGifsUseCase(private val repository: GifFavouriteRepository) {

    fun execute(): Flow<List<FavouriteGif>> {
        return repository.getFavoriteGifs()
    }
}