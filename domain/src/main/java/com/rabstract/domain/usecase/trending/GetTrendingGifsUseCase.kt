package com.rabstract.domain.usecase.trending

import androidx.paging.PagingData
import com.rabstract.core.datatype.Result
import com.rabstract.core.datatype.ResultType
import com.rabstract.data.repository.GifFavouriteRepository
import com.rabstract.data.repository.GifTrendingRepository
import com.rabstract.model.GifData
import kotlinx.coroutines.flow.Flow

class GetTrendingGifsUseCase(private val gifTrendingRepository: GifTrendingRepository,private val gifFavouriteRepository: GifFavouriteRepository) {

    fun execute(query : String?): Flow<PagingData<GifData>> {
        return gifTrendingRepository.fetchTrendingGif(query)
    }
}
