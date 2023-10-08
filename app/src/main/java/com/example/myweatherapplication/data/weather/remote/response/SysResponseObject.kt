package com.example.myweatherapplication.data.weather.remote.response

import com.google.gson.annotations.SerializedName

data class SysResponseObject (
    @SerializedName("type")
    val type : Int?,
    @SerializedName("id")
    val id : Int?,
    @SerializedName("message")
    val message : String?,
    @SerializedName("country")
    val country : String?,
    @SerializedName("sunrise")
    val sunrise : Int?,
    @SerializedName("sunset")
    val sunset : Int?,
)