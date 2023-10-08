package com.example.myweatherapplication.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class MainResponseObject (
    @SerializedName("temp")
    val temp : Double?,
    @SerializedName("feels_like")
    val feelsLike : Double?,
    @SerializedName("temp_min")
    val tempMin : Double?,
    @SerializedName("temp_max")
    val tempMax : Double?,
    @SerializedName("temp_kf")
    val tempKf : Double?,
    @SerializedName("pressure")
    val pressure : Int?,
    @SerializedName("humidity")
    val humidity : Int?,
    @SerializedName("sea_level")
    val seaLevel : Int?,
    @SerializedName("grnd_level")
    val grndLevel : Int?
)