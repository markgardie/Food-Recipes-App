package com.markgardie.graduatework.data.database

import androidx.room.*
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(productEntity: ProductEntity)

    @Delete
    suspend fun removeFromCart(productEntity: ProductEntity)

    @Query("DELETE FROM products_table")
    suspend fun deleteAllFromCart()

    @Query("SELECT * FROM products_table ORDER BY id ASC")
    fun readCart(): Flow<List<ProductEntity>>
}