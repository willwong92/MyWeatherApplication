package com.example.myweatherapplication.domain.model

data class Weather (
    val id : Int,
    val cityName : String,
    val country : String,
    val timeZone : Int,
    val sunrise : Int,
    val sunset : Int,
    val temp : Double,
    val feelsLike : Double,
    val pressure : Int,
    val humidity : Int,
    val description: String,
    val main: String,
    val icon: String,
    val windSpeed: Double,
    val dt : Long,
)