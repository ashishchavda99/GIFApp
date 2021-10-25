package com.rabstract.domain.usecase.trending

import com.rabstract.data.repository.GifFavouriteRepository
import com.rabstract.model.GifData

class RemoveTrendingGifUseCase(private val gifFavouriteRepository: GifFavouriteRepository) {

    suspend fun execute(gifData: GifData): Int {
        return gifFavouriteRepository.deleteFavoriteGif(gifData)
    }
}