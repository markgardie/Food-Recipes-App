package com.markgardie.graduatework

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.markgardie.graduatework.models.FoodRecipe
import com.markgardie.graduatework.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
        var foodRecipe: FoodRecipe
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}