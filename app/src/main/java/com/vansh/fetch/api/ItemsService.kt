package com.vansh.fetch.api

import com.vansh.fetch.data.remote.Item
import com.vansh.fetch.util.Endpoints
import retrofit2.Response
import retrofit2.http.GET

interface ItemsService {

    @GET(Endpoints.HIRING)
    suspend fun getAllItems(): Response<List<Item>>

}