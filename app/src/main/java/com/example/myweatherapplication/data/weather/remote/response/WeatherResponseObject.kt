package com.example.myweatherapplication.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherResponseObject (
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("timezone")
    val timeZone : Int,
    @SerializedName("cod")
    val cod: Int?,
    @SerializedName("coord")
    val coord: CoordResponseObject?,
    @SerializedName("dt")
    val dt : Long?,
    @SerializedName("main")
    val main : MainResponseObject?,
    @SerializedName("weather")
    val weather : List<WeatherListItemResponseObject>?,
    @SerializedName("wind")
    val wind : WindResponseObject?,
    @SerializedName("visibility")
    val visibility : Int?,
    @SerializedName("pop")
    val pop : Double?,
    @SerializedName("sys")
    val sys : SysResponseObject?,
)