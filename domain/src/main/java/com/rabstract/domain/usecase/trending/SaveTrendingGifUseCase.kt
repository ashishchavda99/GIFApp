package com.rabstract.domain.usecase.trending

import com.rabstract.data.repository.GifFavouriteRepository
import com.rabstract.model.GifData

class SaveTrendingGifUseCase(private val gifFavouriteRepository: GifFavouriteRepository) {

    suspend fun execute(gifData: GifData) {
         gifFavouriteRepository.saveFavouriteGif(gifData)
    }
}