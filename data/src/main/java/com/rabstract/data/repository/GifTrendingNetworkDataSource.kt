package com.rabstract.data.repository


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rabstract.core.exceptions.handleNetworkExceptions
import com.rabstract.data.GifAppApi
import com.rabstract.model.GifData
import com.rabstract.core.exceptions.CancelledFetchDataException


class GifTrendingNetworkDataSource(private val gifAppApi: GifAppApi, private val gifFavouriteRepository: GifFavouriteRepository, private val query: String?) : PagingSource<Int, GifData>(){

    override fun getRefreshKey(state: PagingState<Int, GifData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifData> {
        val pageIndex = params.key ?: 0

        return try {
            val response = if (query.isNullOrBlank()) {
                gifAppApi.getTrendingGifs(page = pageIndex)
            } else {
                gifAppApi.search(query = query, page = pageIndex)
            }
            val nextKey = pageIndex + 1
            if (response.body()?.meta?.status == 200) {
                response.body()?.data?.forEach { gifData ->
                    gifData.isFavourite = gifFavouriteRepository.isFavouriteGif(gifData.id)
                }

                LoadResult.Page(
                    data = response.body()!!.data,
                    prevKey = if (pageIndex == 0) null else pageIndex - 1,
                    nextKey = nextKey
                )

            } else {
                LoadResult.Error(
                    CancelledFetchDataException()
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(handleNetworkExceptions(e))
        }
    }
}
