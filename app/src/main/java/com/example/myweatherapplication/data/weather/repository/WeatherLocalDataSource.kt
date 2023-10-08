package com.example.myweatherapplication.data.weather.repository

import com.example.myweatherapplication.data.error.DataNotAvailableException
import com.example.myweatherapplication.domain.util.Result
import com.example.myweatherapplication.data.weather.local.dao.WeatherDao
import com.example.myweatherapplication.data.weather.mapper.toDomain
import com.example.myweatherapplication.data.weather.mapper.toEntity
import com.example.myweatherapplication.domain.model.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class WeatherLocalDataSource(
    private val weatherDao: WeatherDao,
    private val coroutineDispatcher: CoroutineDispatcher
) : WeatherDataSource.Local {
    override suspend fun getWeather(): Result<Weather> = withContext(coroutineDispatcher){
        val weather = weatherDao.getWeather()
        return@withContext weather?.let {
            Result.Success(weather.toDomain())
        } ?: Result.Error(DataNotAvailableException("Data Not Available"))

    }
    override suspend fun saveWeather(weather: Weather) = withContext(coroutineDispatcher) {
        weatherDao.insertWeather(weather.toEntity())
    }
    override suspend fun getWeatherDetails(weatherId: Int): Result<Weather> = withContext(coroutineDispatcher) {
        return@withContext weatherDao.getWeatherById(weatherId)?.let {
            Result.Success(it.toDomain())
        } ?: Result.Error(DataNotAvailableException("Last Location Unknown"))
    }
}