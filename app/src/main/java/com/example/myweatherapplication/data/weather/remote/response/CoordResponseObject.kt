package com.example.myweatherapplication.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class CoordResponseObject (
    @SerializedName("lon")
    val lon : Double?,
    @SerializedName("lat")
    val lat : Double?
)