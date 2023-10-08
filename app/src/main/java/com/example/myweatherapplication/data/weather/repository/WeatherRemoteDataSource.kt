package com.example.myweatherapplication.data.weather.repository

import com.example.myweatherapplication.data.error.DataNotAvailableException
import com.example.myweatherapplication.data.weather.remote.api.WeatherApiService
import com.example.myweatherapplication.data.weather.mapper.toDomain
import com.example.myweatherapplication.domain.model.Weather
import com.example.myweatherapplication.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class WeatherRemoteDataSource (
    private val weatherApiService: WeatherApiService,
    private val coroutineDispatcher: CoroutineDispatcher
) : WeatherDataSource.Remote {

    override suspend fun searchWeatherByCity(
        city: String
    ): Result<Weather> = withContext(coroutineDispatcher) {
        return@withContext try {
            val result = weatherApiService.getWeatherByCity(city)
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            val error = e.message?.let { it } ?: "Data Not Available"
            Result.Error(DataNotAvailableException(error))
        }
    }

    override suspend fun searchWeatherByCoordinates(
        lat: Double,
        lon: Double
    ): Result<Weather> = withContext(coroutineDispatcher) {
        return@withContext try {
            val result = weatherApiService.getWeatherByCoordinates(lat, lon)
            Result.Success(result.toDomain())
        } catch (e: Exception) {
            val error = e.message?.let { it } ?: "Data Not Available"
            Result.Error(DataNotAvailableException(error))
        }
    }
}