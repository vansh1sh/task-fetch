package com.vansh.fetch.repository

import com.vansh.fetch.data.remote.Item
import com.vansh.fetch.util.APIResult
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getAllItems(): Flow<APIResult<List<Item>>>
}