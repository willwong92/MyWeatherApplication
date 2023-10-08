
package com.example.myweatherapplication.domain.usecase

import com.example.myweatherapplication.domain.model.Weather
import com.example.myweatherapplication.domain.repository.WeatherRepository
import com.example.myweatherapplication.domain.util.Result

class GetWeatherByIdUseCase constructor(
    private val repository: WeatherRepository
    ){
    suspend operator fun invoke(id: Int): Result<Weather> = repository.getWeatherById(id)
}
