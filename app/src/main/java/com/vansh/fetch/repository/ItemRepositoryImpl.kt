package com.vansh.fetch.repository

import android.util.Log
import com.vansh.fetch.api.ItemsService
import com.vansh.fetch.data.remote.Item
import com.vansh.fetch.util.APIResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemsService: ItemsService
) : ItemRepository {

    companion object {
        private const val TAG = "ItemRepositoryImpl"
    }

    override suspend fun getAllItems(): Flow<APIResult<List<Item>>> {
        return flow {
            emit(APIResult.Loading())
            val response = itemsService.getAllItems()
            try {
                if (response.isSuccessful && response.body() != null) {
                    emit(APIResult.Success(data = response.body()!!))
                } else {
                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    emit(APIResult.Error(message = errorObj.getString("message")))
                }
            } catch (exception: JSONException) {
                emit(APIResult.Error(message = "Something Went Wrong!"))
                Log.e(TAG, "getAllItems: ${exception.message}")
            } catch (exception: Exception) {
                emit(APIResult.Error(message = "Something Went Wrong!"))
                Log.e(TAG, "getAllItems: ${exception.message}")
            }
        }
    }
}
