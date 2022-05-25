package com.markgardie.graduatework.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.markgardie.graduatework.models.ProductsList
import com.markgardie.graduatework.util.Constants
import com.markgardie.graduatework.util.Constants.Companion.ORDERS_TABLE

@Entity(tableName = ORDERS_TABLE)
class OrdersEntity(

        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var name: String,
        var phone: String,
        var address: String,
        var products: ProductsList
)