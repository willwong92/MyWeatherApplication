package com.example.myweatherapplication.presentation.ui.state

data class WeatherUIState(
    val id : Int = 0,
    val cityName : String = "",
    val country : String = "",
    val temp : Double = 0.0,
    val feelsLike : Double = 0.0,
    val pressure : Int = 0,
    val humidity : Int = 0,
    val description: String = "",
    val main: String = "",
    val icon: String = "",
    val windSpeed: Double = 0.0,
    val dt : Long = 0L,
    val errorMessage: String? = null
)