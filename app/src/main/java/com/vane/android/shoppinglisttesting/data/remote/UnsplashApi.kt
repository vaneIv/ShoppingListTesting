package com.vane.android.shoppinglisttesting.data.remote

import com.vane.android.shoppinglisttesting.BuildConfig
import com.vane.android.shoppinglisttesting.data.remote.responses.UnsplashResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
        const val CLIENT_ID = BuildConfig.UNSPLACH_ACCESS_API_KEY
    }

    // We use the @Headers simply because the API requires it, and is stated in
    // the Api's documentation
    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchForImage(
        @Query("query") query: String,
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
    ): Response<UnsplashResponse>
}