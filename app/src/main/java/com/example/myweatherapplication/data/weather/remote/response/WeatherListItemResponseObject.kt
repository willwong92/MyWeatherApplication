package com.example.myweatherapplication.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherListItemResponseObject (
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)