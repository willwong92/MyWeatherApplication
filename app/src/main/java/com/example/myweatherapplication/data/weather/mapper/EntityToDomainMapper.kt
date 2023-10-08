package com.example.myweatherapplication.data.weather.mapper

import com.example.myweatherapplication.data.weather.local.entity.WeatherEntity
import com.example.myweatherapplication.domain.model.*

fun WeatherEntity.toDomain() = Weather(
    id = this.id,
    cityName = this.cityName,
    country = this.country,
    timeZone = this.timeZone,
    sunrise = this.sunrise,
    sunset = this.sunset,
    temp = this.temp,
    feelsLike = this.feelsLike,
    pressure = this.pressure,
    humidity = this.humidity,
    description = this.description,
    main = this.main,
    icon = this.icon,
    windSpeed = this.windSpeed,
    dt = this.dateMillis
)