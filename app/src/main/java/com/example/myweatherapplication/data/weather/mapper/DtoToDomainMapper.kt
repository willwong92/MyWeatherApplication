package com.example.myweatherapplication.data.weather.mapper

import com.example.myweatherapplication.data.weather.remote.response.WeatherResponseObject
import com.example.myweatherapplication.domain.model.*

fun WeatherResponseObject.toDomain() = Weather (
    id = this.id,
    cityName = this.name,
    country = this.sys?.country.orEmpty(),
    timeZone = this.timeZone,
    sunrise = this.sys?.sunrise ?: 0,
    sunset = this.sys?.sunset ?: 0,
    temp = this.main?.temp ?: 0.0,
    feelsLike = this.main?.feelsLike ?: 0.0,
    pressure = this.main?.pressure ?: 0,
    humidity = this.main?.humidity ?: 0,
    description = this.weather?.first()?.description.orEmpty(),
    main = this.weather?.first()?.main.orEmpty(),
    icon = this.weather?.first()?.icon.orEmpty(),
    windSpeed = this.wind?.speed ?: 0.0,
    dt = this.dt ?: 0L
)