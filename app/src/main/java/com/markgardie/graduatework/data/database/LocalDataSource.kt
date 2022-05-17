package com.markgardie.graduatework.data.database

import com.markgardie.graduatework.data.database.daos.ProductsDao
import com.markgardie.graduatework.data.database.daos.RecipesDao
import com.markgardie.graduatework.data.database.entities.FavoritesEntity
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao,
    private val productsDao: ProductsDao
) {


    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    fun readCart(): Flow<List<ProductEntity>> {
        return productsDao.readCart()
    }


    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)

    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun addToCart(productEntity: ProductEntity) {
        productsDao.addToCart(productEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun removeFromCart(productEntity: ProductEntity) {
        productsDao.removeFromCart(productEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }

    suspend fun removeAllFromCart() {
        productsDao.removeAllFromCart()
    }
}