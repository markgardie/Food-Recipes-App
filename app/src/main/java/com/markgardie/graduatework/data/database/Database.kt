package com.markgardie.graduatework.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.markgardie.graduatework.data.database.daos.OrdersDao
import com.markgardie.graduatework.data.database.daos.ProductsDao
import com.markgardie.graduatework.data.database.daos.RecipesDao
import com.markgardie.graduatework.data.database.entities.FavoritesEntity
import com.markgardie.graduatework.data.database.entities.OrdersEntity
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.data.database.entities.RecipesEntity

@Database(
        entities = [RecipesEntity::class,
            FavoritesEntity::class,
            ProductEntity::class,
            OrdersEntity::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class Database : RoomDatabase(){

    abstract fun recipesDao() : RecipesDao

    abstract fun productsDao(): ProductsDao

    abstract fun ordersDao(): OrdersDao
}