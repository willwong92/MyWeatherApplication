package com.example.myweatherapplication.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapplication.domain.repository.WeatherPreference
import com.example.myweatherapplication.domain.usecase.GetWeatherByIdUseCase
import com.example.myweatherapplication.domain.usecase.SearchWeatherByCityUseCase
import com.example.myweatherapplication.domain.usecase.SearchWeatherByCoordinatesUseCase
import com.example.myweatherapplication.domain.util.onError
import com.example.myweatherapplication.domain.util.onSuccess
import com.example.myweatherapplication.presentation.ui.state.WeatherUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val searchWeatherByCityUseCase: SearchWeatherByCityUseCase,
    private val searchWeatherByCoordinatesUseCase: SearchWeatherByCoordinatesUseCase,
    private val getWeatherByIdUseCase: GetWeatherByIdUseCase,
    private val dataStore: WeatherPreference
) : ViewModel() {

    private val _uiState: MutableStateFlow<WeatherUIState> = MutableStateFlow(WeatherUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getWeatherDetailById()
    }

    fun getWeatherByCoordinates(lat: Double, lon: Double) {
        viewModelScope.launch {
            searchWeatherByCoordinatesUseCase.invoke(lat, lon).onSuccess { weather ->
                _uiState.update {
                    it.copy(
                        id = weather.id,
                        cityName = weather.cityName,
                        country = weather.country,
                        temp = weather.temp,
                        feelsLike = weather.feelsLike,
                        pressure = weather.pressure,
                        humidity = weather.humidity,
                        description = weather.description,
                        main = weather.main,
                        icon = weather.icon,
                        windSpeed = weather.windSpeed,
                        dt = weather.dt,
                        errorMessage = null
                    )
                }
                saveWeatherId(weather.id)
            }.onError { throwable ->
                _uiState.update {
                    it.copy(errorMessage = throwable.message)
                } }
        }
    }

    fun getWeatherByCity(city: String) {
        viewModelScope.launch {
            searchWeatherByCityUseCase.invoke(city).onSuccess { weather ->
                _uiState.update {
                    it.copy(
                        id = weather.id,
                        cityName = weather.cityName,
                        country = weather.country,
                        temp = weather.temp,
                        feelsLike = weather.feelsLike,
                        pressure = weather.pressure,
                        humidity = weather.humidity,
                        description = weather.description,
                        main = weather.main,
                        icon = weather.icon,
                        windSpeed = weather.windSpeed,
                        dt = weather.dt,
                        errorMessage = null
                    )
                }
                saveWeatherId(weather.id)
            }.onError { throwable ->
                _uiState.update {
                    it.copy(errorMessage = throwable.message)
            } }
        }
    }

    private fun saveWeatherId(id: Int) {
        viewModelScope.launch {
            dataStore.saveWeatherId(id)
        }
    }

    private fun getWeatherDetailById() {
        viewModelScope.launch {
            dataStore.weatherId().collect { id ->
                getWeatherByIdUseCase.invoke(id).onSuccess { weather ->
                    _uiState.value = WeatherUIState(
                        id = weather.id,
                        cityName = weather.cityName,
                        country = weather.country,
                        temp = weather.temp,
                        feelsLike = weather.feelsLike,
                        pressure = weather.pressure,
                        humidity = weather.humidity,
                        description = weather.description,
                        main = weather.main,
                        icon = weather.icon,
                        windSpeed = weather.windSpeed,
                        dt = weather.dt,
                        errorMessage = null

                    )
                }.onError { throwable ->
                    _uiState.update {
                        it.copy(errorMessage = throwable.message)
                    }
                }
            }
        }
    }
}