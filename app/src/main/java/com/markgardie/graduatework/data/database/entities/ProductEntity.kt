package com.markgardie.graduatework.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.markgardie.graduatework.models.Product
import com.markgardie.graduatework.util.Constants.Companion.PRODUCTS_TABLE

@Entity(tableName = PRODUCTS_TABLE)
class ProductEntity(

        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var product: Product
)