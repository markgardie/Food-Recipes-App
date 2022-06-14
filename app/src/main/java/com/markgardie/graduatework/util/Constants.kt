package com.markgardie.graduatework.util

class Constants {

    companion object {
        const val API_KEY = "c9bb43e5ef4f479fb41e55e3d11b760f"
        const val BASE_URL = "https://api.spoonacular.com"
        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
        const val PRODUCTS_URL = "https://stores-api.zakaz.ua"

        const val RECIPE_RESULT_KEY = "recipeBundle"
        const val INGREDIENTS_BUNDLE = "ingredientsBundle"

        //API Query Keys
        const val QUERY_SEARCH = "query"
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //Shops Keys
        const val EKO_MARKET = "48280187"

        //ROOM Database
        const val DATABASE_NAME = "database"
        const val RECIPES_TABLE = "recipes_table"
        const val FAVORITE_RECIPES_TABLE = "favorite_recipes_table"
        const val PRODUCTS_TABLE = "products_table"
        const val ORDERS_TABLE = "orders_table"

        // Bottom Sheet Preferences
        const val DEFAULT_RECIPES_NUMBER = "50"
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"

        const val PREFERENCES_NAME = "foody_preferences"
        const val PREFERENCES_MEAL_TYPE = "mealType"
        const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
        const val PREFERENCES_DIET_TYPE = "dietType"
        const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"
        const val PREFERENCES_BACK_ONLINE = "backOnline"

    }
}