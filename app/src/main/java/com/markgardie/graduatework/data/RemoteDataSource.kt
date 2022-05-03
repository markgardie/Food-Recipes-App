package com.markgardie.graduatework.data

import com.markgardie.graduatework.data.network.FoodRecipesApi
import com.markgardie.graduatework.data.network.ProductsApi
import com.markgardie.graduatework.models.FoodRecipe
import com.markgardie.graduatework.models.ProductsList
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi,
    private val productsApi: ProductsApi
    ) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

    suspend fun getProducts(shopId: String, title: String): Response<ProductsList> {
        return productsApi.getProducts(shopId, title)
    }

}