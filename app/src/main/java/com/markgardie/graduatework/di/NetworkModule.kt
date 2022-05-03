package com.markgardie.graduatework.di

import com.markgardie.graduatework.util.Constants.Companion.BASE_URL
import com.markgardie.graduatework.data.network.FoodRecipesApi
import com.markgardie.graduatework.data.network.ProductsApi
import com.markgardie.graduatework.util.Constants.Companion.PRODUCTS_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SpoonacularRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ZakazUaRetrofit

    @Singleton
    @SpoonacularRetrofit
    @Provides
    fun provideSpoonacularRetrofitInstance(okHttpClient: OkHttpClient,
                                           gsonConverterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }


    @Singleton
    @Provides
    fun provideSpoonacularApiService(@SpoonacularRetrofit retrofit: Retrofit): FoodRecipesApi {
        return retrofit.create(FoodRecipesApi::class.java)
    }

    @Singleton
    @ZakazUaRetrofit
    @Provides
    fun provideZakazUaRetrofitInstance(okHttpClient: OkHttpClient,
                                       gsonConverterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(PRODUCTS_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Singleton
    @Provides
    fun provideZakazUaApiService(@ZakazUaRetrofit retrofit: Retrofit): ProductsApi {
        return retrofit.create(ProductsApi::class.java)
    }

}