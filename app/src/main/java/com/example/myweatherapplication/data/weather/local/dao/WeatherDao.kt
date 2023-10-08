package com.example.myweatherapplication.data.weather.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.myweatherapplication.data.weather.local.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table")
    fun getWeather(): WeatherEntity?

    @Query("SELECT * FROM weather_table WHERE id = :id")
    fun getWeatherById(id: Int): WeatherEntity?

    @Insert(onConflict = REPLACE)
    fun insertWeather(weather: WeatherEntity)

    @Update
    fun updateWeather(weather: WeatherEntity)

    @Delete
    fun deleteWeather(weather: WeatherEntity)

    @Query("DELETE FROM weather_table")
    fun deleteAll()

    @Query("Select count(*) from weather_table")
    fun getCount(): Int
}