package com.vane.android.shoppinglisttesting.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.vane.android.shoppinglisttesting.data.local.ShoppingDao
import com.vane.android.shoppinglisttesting.data.local.ShoppingItem
import com.vane.android.shoppinglisttesting.data.remote.UnsplashApi
import com.vane.android.shoppinglisttesting.data.remote.responses.UnsplashResponse
import com.vane.android.shoppinglisttesting.other.Resource

class DefaultShoppingRepository(
    private val shoppingDao: ShoppingDao,
    private val unsplashApi: UnsplashApi
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<UnsplashResponse> {
        return try {
            val response = unsplashApi.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch (e: Exception) {
            Log.e("EXCEPTION", "EXCEPTION:", e)
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

}