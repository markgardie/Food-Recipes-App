package com.markgardie.graduatework.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.markgardie.graduatework.models.Result
import com.markgardie.graduatework.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var result: Result
)