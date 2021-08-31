package com.vane.android.shoppinglisttesting.repositories

import androidx.lifecycle.LiveData
import com.vane.android.shoppinglisttesting.data.local.ShoppingItem
import com.vane.android.shoppinglisttesting.data.remote.responses.UnsplashResponse
import com.vane.android.shoppinglisttesting.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<UnsplashResponse>

}