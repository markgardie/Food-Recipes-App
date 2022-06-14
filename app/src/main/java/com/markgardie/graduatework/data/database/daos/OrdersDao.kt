package com.markgardie.graduatework.data.database.daos

import androidx.room.*
import com.markgardie.graduatework.data.database.entities.OrdersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(ordersEntity: OrdersEntity)

    @Delete
    suspend fun deleteOrder(ordersEntity: OrdersEntity)

    @Query("DELETE FROM orders_table")
    suspend fun deleteAllOrders()

    @Query("SELECT * FROM orders_table ORDER BY id ASC")
    fun readOrders(): Flow<List<OrdersEntity>>
}