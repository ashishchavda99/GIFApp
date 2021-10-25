package com.rabstract.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rabstract.data.GifAppApi
import com.rabstract.model.GifData
import kotlinx.coroutines.flow.Flow


class GifTrendingRepository (private val api: GifAppApi, private val favouriteRepository: GifFavouriteRepository){
    fun fetchTrendingGif(query: String?): Flow<PagingData<GifData>> = Pager(
        config = PagingConfig(
            pageSize = 1,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            GifTrendingNetworkDataSource(api, favouriteRepository,query)
        }
    ).flow
}
