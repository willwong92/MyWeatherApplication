package com.example.myweatherapplication.data.weather.repository

import com.example.myweatherapplication.domain.util.Result
import com.example.myweatherapplication.domain.model.Weather
import com.example.myweatherapplication.domain.repository.WeatherRepository
import com.example.myweatherapplication.domain.util.onSuccess
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val local: WeatherDataSource.Local,
    private val remote: WeatherDataSource.Remote,
    ): WeatherRepository {

    override suspend fun getWeatherByCity(city: String): Result<Weather> {
        return remote.searchWeatherByCity(city).onSuccess { local.saveWeather(it) }
    }

    override suspend fun getWeatherByCoordinates(lat: Double, lon: Double): Result<Weather> {
        return remote.searchWeatherByCoordinates(lat, lon).onSuccess { local.saveWeather(it) }
    }

    override suspend fun getWeatherById(id: Int): Result<Weather> {
        return local.getWeatherDetails(id)
    }
}