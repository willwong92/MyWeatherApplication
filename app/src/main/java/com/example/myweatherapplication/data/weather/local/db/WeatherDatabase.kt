package com.example.myweatherapplication.data.weather.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myweatherapplication.data.weather.local.dao.WeatherDao
import com.example.myweatherapplication.data.weather.local.entity.WeatherEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun weatherDao(): WeatherDao
}