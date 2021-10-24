package com.rabstract.data.repository

import com.rabstract.data.base.BaseUTTest
import com.rabstract.data.di.configureTestAppComponent
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection


@RunWith(JUnit4::class)
class TrendingGifRepositoryTest : BaseUTTest() {

    private lateinit var mRepo: GifTrendingRepository


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun start() {
        super.setUp()
        startKoin {
            modules(configureTestAppComponent(getMockWebServerUrl()))
        }
    }

    @InternalCoroutinesApi
    @Test
    fun test_gif_trending_api_success_response() = runBlocking<Unit> {

        mockNetworkResponseWithFileContent("success_gif_trending_response.json", HttpURLConnection.HTTP_OK)
        mRepo = GifTrendingRepositoryImpl()

        val dataReceived = mRepo.fetchTrendingGifs("")

        assertNotNull(dataReceived)
    }

    @InternalCoroutinesApi
    @Test
    fun test_gif_trending_api_fail_response() = runBlocking<Unit> {

        mockNetworkResponseWithFileContent("fail_resp_list.json", HttpURLConnection.HTTP_OK)
        mRepo = GifTrendingRepositoryImpl()

        val dataReceived = mRepo.fetchTrendingGifs("")
        assertNotNull(dataReceived)
    }
    @Test
    fun test_gif_search_api_success_response() = runBlocking<Unit> {
        mockNetworkResponseWithFileContent("success_gif_search_response.json", HttpURLConnection.HTTP_OK)
        mRepo = GifTrendingRepositoryImpl()
        val dataReceived = mRepo.fetchTrendingGifs("love")
        assertNotNull(dataReceived)
    }

    @Test
    fun test_gif_search_api_fail_response() = runBlocking<Unit> {
        mockNetworkResponseWithFileContent("fail_resp_list.json", HttpURLConnection.HTTP_OK)
        mRepo = GifTrendingRepositoryImpl()
        val dataReceived = mRepo.fetchTrendingGifs("love")
        assertNotNull(dataReceived)
    }
}
