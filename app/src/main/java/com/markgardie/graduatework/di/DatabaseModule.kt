package com.markgardie.graduatework.di

import android.content.Context
import androidx.room.Room
import com.markgardie.graduatework.data.database.Database
import com.markgardie.graduatework.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
            @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context,
            Database::class.java,
            DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRecipesDao(database: Database) = database.recipesDao()


    @Singleton
    @Provides
    fun provideProductsDao(database: Database) = database.productsDao()

}