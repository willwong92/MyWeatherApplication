package com.example.myweatherapplication.data.weather.remote.api

import com.example.myweatherapplication.data.Constants
import com.example.myweatherapplication.data.weather.remote.response.WeatherResponseObject
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeatherByCity(@Query(Constants.QUERY) city: String): WeatherResponseObject

    @GET("weather")
    suspend fun getWeatherByCoordinates(@Query(Constants.LAT) lat: Double, @Query(Constants.LON) lon: Double): WeatherResponseObject
}