package com.rabstract.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rabstract.model.GifData
import kotlinx.coroutines.flow.Flow


interface GifTrendingRepository {

    suspend fun fetchTrendingGifs(query: String?): Flow<PagingData<GifData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GifDataPagingSource()
            }
        ).flow
    }
}
