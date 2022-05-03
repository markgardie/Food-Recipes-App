package com.markgardie.graduatework.data.network

import com.markgardie.graduatework.models.ProductsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {

    @GET("/stores/{store_id}/products/search")
    suspend fun getProducts(
            @Path("store_id") shopId: String,
            @Query("q") title: String
    ): Response<ProductsList>

}