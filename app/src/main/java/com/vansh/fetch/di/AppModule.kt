package com.vansh.fetch.di

import com.vansh.fetch.api.ItemsService
import com.vansh.fetch.repository.ItemRepository
import com.vansh.fetch.repository.ItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesItemRepository(itemsService: ItemsService): ItemRepository {
        return ItemRepositoryImpl(itemsService)
    }

}