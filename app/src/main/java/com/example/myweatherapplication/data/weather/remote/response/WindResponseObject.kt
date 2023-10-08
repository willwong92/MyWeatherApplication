package com.example.myweatherapplication.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class WindResponseObject (
    @SerializedName("speed")
    val speed : Double?,
    @SerializedName("deg")
    val deg : Int?,
    @SerializedName("gust")
    val gust : Double?
)