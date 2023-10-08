package com.example.myweatherapplication.di.module

import android.content.Context
import androidx.room.Room
import com.example.myweatherapplication.data.weather.local.dao.WeatherDao
import com.example.myweatherapplication.data.weather.local.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(app, WeatherDatabase::class.java, "WEATHER_DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(
        database: WeatherDatabase
    ): WeatherDao {
        return database.weatherDao()
    }
}