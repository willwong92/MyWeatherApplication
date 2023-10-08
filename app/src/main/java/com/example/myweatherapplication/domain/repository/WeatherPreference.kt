package com.example.myweatherapplication.domain.repository

import kotlinx.coroutines.flow.Flow

interface WeatherPreference {
    fun weatherId(): Flow<Int>
    suspend fun saveWeatherId(id: Int)
}