package com.example.finalplayground.di

import com.example.finalplayground.data.network.CarsApi
import com.example.finalplayground.data.network.CarsApi.Companion.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    /**
     * Initialises Retrofit API
     */
    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideCarApi(): CarsApi {
        val contentType = MediaType.get("application/json")

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
            .create(CarsApi::class.java)
    }
}
