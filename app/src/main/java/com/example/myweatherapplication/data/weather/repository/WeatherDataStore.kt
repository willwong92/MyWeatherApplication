package com.example.myweatherapplication.data.weather.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.myweatherapplication.domain.repository.WeatherPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PREFERENCES_NAME = "weather_preferences"
private const val KEY_WEATHER_ID = "weatherId"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
class WeatherDataStore @Inject constructor(
    private val context: Context
) : WeatherPreference {
    override suspend fun saveWeatherId(id: Int) {
        val preferenceKey = intPreferencesKey(KEY_WEATHER_ID)
        context.dataStore.edit { preference ->
            preference[preferenceKey] = id
        }
    }

    override fun weatherId(): Flow<Int> {
        return context.dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preference ->
                val preferenceKey = intPreferencesKey(KEY_WEATHER_ID)
                preference[preferenceKey] ?: 0
            }
    }
}