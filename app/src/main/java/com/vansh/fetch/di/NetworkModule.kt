package com.vansh.fetch.di

import com.vansh.fetch.api.ItemsService
import com.vansh.fetch.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun providesItemService(
        retrofitBuilder: Retrofit.Builder
    ): ItemsService {
        return retrofitBuilder
            .build()
            .create(ItemsService::class.java)
    }
}
