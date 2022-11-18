package com.uli.rickandmortytest.data.di

import androidx.paging.PagingSource
import com.uli.rickandmortytest.BuildConfig.BASE_URL
import com.uli.rickandmortytest.data.network.apiService.RickAndMortyApi
import com.uli.rickandmortytest.data.network.repository.RickRepositoryImpl
import com.uli.rickandmortytest.data.source.CharacterPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): RickAndMortyApi {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build().create(RickAndMortyApi::class.java)
    }

    @Provides
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }.build()
    }

    @Provides
    @Singleton
    fun provideRepository(api: RickAndMortyApi): RickRepositoryImpl {
        return RickRepositoryImpl(api)
    }
}