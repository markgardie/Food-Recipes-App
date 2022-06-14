package com.markgardie.graduatework.data.database

import com.markgardie.graduatework.data.database.daos.OrdersDao
import com.markgardie.graduatework.data.database.daos.ProductsDao
import com.markgardie.graduatework.data.database.daos.RecipesDao
import com.markgardie.graduatework.data.database.entities.FavoritesEntity
import com.markgardie.graduatework.data.database.entities.OrdersEntity
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao,
    private val productsDao: ProductsDao,
    private val ordersDao: OrdersDao
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

    fun readOrders(): Flow<List<OrdersEntity>> {
        return ordersDao.readOrders()
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

    suspend fun addOrder(ordersEntity: OrdersEntity) {
        ordersDao.addOrder(ordersEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun removeFromCart(productEntity: ProductEntity) {
        productsDao.removeFromCart(productEntity)
    }

    suspend fun deleteOrder(ordersEntity: OrdersEntity) {
        ordersDao.deleteOrder(ordersEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }

    suspend fun removeAllFromCart() {
        productsDao.removeAllFromCart()
    }

    suspend fun deleteAllOrders() {
        ordersDao.deleteAllOrders()
    }
}