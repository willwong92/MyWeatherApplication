package com.example.myweatherapplication.di.module

import android.content.Context
import com.example.myweatherapplication.data.weather.repository.WeatherDataStore
import com.example.myweatherapplication.domain.repository.WeatherPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) : WeatherPreference {
        return WeatherDataStore(context)
    }
}

