package com.rabstract.data.di

import android.app.Application
import androidx.room.Room
import com.rabstract.data.BuildConfig
import com.rabstract.data.GifAppApi
import com.rabstract.data.db.GifDataBase
import com.rabstract.data.db.dao.FavouriteGifDao
import com.rabstract.data.repository.GifFavouriteRepository
import com.rabstract.data.repository.GifFavouriteRepositoryImpl
import com.rabstract.data.repository.GifTrendingRepository
import com.rabstract.data.repository.GifTrendingRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    fun provideDatabase(application: Application): GifDataBase {
        return Room.databaseBuilder(application, GifDataBase::class.java, "gifdata")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { provideDatabase(androidApplication()) }
    fun provideFavouriteGifDao(database: GifDataBase): FavouriteGifDao {
        return database.getFavouriteGifDao()
    }

    single { provideFavouriteGifDao(get()) }


    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        provideRetrofit(get(),BuildConfig.BASE_URL)
    }

    fun provideGifFavouriteRepository(): GifFavouriteRepository {
        return GifFavouriteRepositoryImpl()
    }
    factory { provideGifFavouriteRepository() }

    fun provideGifTrendingRepository(): GifTrendingRepository {
        return GifTrendingRepositoryImpl()
    }
    factory { provideGifTrendingRepository() }

    fun provideGifAppApi(retrofit: Retrofit): GifAppApi {
        return retrofit.create(GifAppApi::class.java)
    }
    single {
        provideGifAppApi(get())
    }
}






