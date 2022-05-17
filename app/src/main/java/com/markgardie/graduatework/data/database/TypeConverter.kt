package com.markgardie.graduatework.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.markgardie.graduatework.models.FoodRecipe
import com.markgardie.graduatework.models.Product
import com.markgardie.graduatework.models.ProductsList
import com.markgardie.graduatework.models.Result

class TypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe) : String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun productListToString(productsList: ProductsList): String {
        return gson.toJson(productsList)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        val lisType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, lisType)
    }

    @TypeConverter
    fun stringToProductList(data: String): ProductsList {
        val lisType = object : TypeToken<ProductsList>() {}.type
        return gson.fromJson(data, lisType)
    }

    @TypeConverter
    fun resultToString(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun productToString(product: Product): String {
        return gson.toJson(product)
    }

    @TypeConverter
    fun stringToResult(data: String): Result {
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringToProduct(data: String): Product {
        val listType = object : TypeToken<Product>() {}.type
        return gson.fromJson(data, listType)
    }

}