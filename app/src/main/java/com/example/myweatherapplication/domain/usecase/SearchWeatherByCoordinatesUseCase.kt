package com.example.myweatherapplication.domain.usecase

import com.example.myweatherapplication.domain.model.Weather
import com.example.myweatherapplication.domain.repository.WeatherRepository
import com.example.myweatherapplication.domain.util.Result

class SearchWeatherByCoordinatesUseCase constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): Result<Weather> = repository.getWeatherByCoordinates(lat, lon)
}