package com.example.myweatherapplication.domain.usecase

import com.example.myweatherapplication.domain.util.Result
import com.example.myweatherapplication.domain.model.Weather
import com.example.myweatherapplication.domain.repository.WeatherRepository

class SearchWeatherByCityUseCase constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<Weather> = repository.getWeatherByCity(city)
}