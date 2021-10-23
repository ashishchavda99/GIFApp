package com.rabstract.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rabstract.data.GifAppApi
import com.rabstract.model.GifData
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException
import java.io.IOException as IOException1

class GifDataPagingSource(private val query: String? = null,) : PagingSource<Int, GifData>() ,KoinComponent {
    val api: GifAppApi by inject()
    override fun getRefreshKey(state: PagingState<Int, GifData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifData> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 0
        return try {
            val response = if (query.isNullOrBlank()){
                api.getTrendingGifs(page = page)
            }else{
                api.search(query = query,page = page)
            }
            LoadResult.Page(
                response.body()?.data ?: mutableListOf(), prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.body()?.data?.isEmpty() == true) null else page + 1
            )
        } catch (exception: IOException1) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}
