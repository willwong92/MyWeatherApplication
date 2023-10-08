package com.example.myweatherapplication.data.weather.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo("city")
    val cityName : String,

    @ColumnInfo("country")
    val country : String,

    @ColumnInfo("timezone")
    val timeZone: Int,

    @ColumnInfo("main")
    val main: String,

    @ColumnInfo(name = "temp")
    val temp: Double,

    @ColumnInfo(name = "feels_like")
    val feelsLike : Double,

    @ColumnInfo(name = "pressure")
    val pressure: Int,

    @ColumnInfo(name = "humidity")
    val humidity: Int,

    @ColumnInfo(name = "speed")
    val windSpeed: Double,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "sunrise")
    val sunrise: Int,

    @ColumnInfo(name = "sunset")
    val sunset: Int,

    @ColumnInfo(name ="dt")
    val dateMillis: Long,

    @ColumnInfo(name = "icon")
    val icon : String
)