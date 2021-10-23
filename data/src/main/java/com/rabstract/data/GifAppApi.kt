package com.rabstract.data

import com.rabstract.model.TrendingGif
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifAppApi {

    // For getting trending api response
    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String =
            BuildConfig.ACCESS_KEY,
        @Query("offset") page: Int = 0,
        @Query("limit") size: Int = 8
    ): Response<TrendingGif>

    // for getting search result for given query for gif api
    @GET("search")
    suspend fun search(
        @Query("api_key") apiKey: String =
            BuildConfig.ACCESS_KEY,
        @Query("q") query: String?,
        @Query("offset") page: Int = 0,
        @Query("limit") size: Int = 8
    ): Response<TrendingGif>

}