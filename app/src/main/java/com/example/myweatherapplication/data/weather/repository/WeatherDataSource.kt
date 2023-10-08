package com.example.myweatherapplication.data.weather.repository

import com.example.myweatherapplication.domain.util.Result
import com.example.myweatherapplication.domain.model.Weather

interface WeatherDataSource {
    interface Remote {
        suspend fun searchWeatherByCity(city: String): Result<Weather>
        suspend fun searchWeatherByCoordinates(lat: Double, lon: Double): Result<Weather>
    }
    interface Local {
        suspend fun getWeather(): Result<Weather>
        suspend fun getWeatherDetails(weatherId: Int): Result<Weather>
        suspend fun saveWeather(weather: Weather)
    }
}