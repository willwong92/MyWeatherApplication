package com.example.myweatherapplication.domain.repository

import com.example.myweatherapplication.domain.util.Result
import com.example.myweatherapplication.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String): Result<Weather>
    suspend fun getWeatherByCoordinates(lat: Double, lon: Double): Result<Weather>
    suspend fun getWeatherById(id: Int): Result<Weather>
}