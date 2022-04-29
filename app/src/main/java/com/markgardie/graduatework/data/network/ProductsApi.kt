package com.markgardie.graduatework.data.network

import com.markgardie.graduatework.models.Product
import com.markgardie.graduatework.models.ProductsList
import com.markgardie.graduatework.util.Constants.Companion.EKO_MARKET
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {

    @GET("/{shop_id}/products/search")
    suspend fun getProducts(
            @Path("shop_id") shopId: String,
            @Query("q") title: String
    ): Response<ProductsList>

}