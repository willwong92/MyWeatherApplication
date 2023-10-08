package com.example.myweatherapplication.di.module

import com.example.myweatherapplication.data.weather.local.dao.WeatherDao
import com.example.myweatherapplication.data.weather.remote.api.WeatherApiService
import com.example.myweatherapplication.data.weather.repository.WeatherDataSource
import com.example.myweatherapplication.data.weather.repository.WeatherLocalDataSource
import com.example.myweatherapplication.data.weather.repository.WeatherRemoteDataSource
import com.example.myweatherapplication.data.weather.repository.WeatherRepositoryImpl
import com.example.myweatherapplication.domain.repository.WeatherRepository
import com.example.myweatherapplication.domain.usecase.GetWeatherByIdUseCase
import com.example.myweatherapplication.domain.usecase.SearchWeatherByCityUseCase
import com.example.myweatherapplication.domain.usecase.SearchWeatherByCoordinatesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideWeatherLocalDataSource(
        weatherDao: WeatherDao,
        @Named("IO")
        coroutineDispatcher: CoroutineDispatcher
    ): WeatherDataSource.Local {
        return WeatherLocalDataSource(weatherDao, coroutineDispatcher)
    }


    @Provides
    @Singleton
    fun provideWeatherRemoteDataSource(
        weatherApiService: WeatherApiService,
        @Named("IO")
        dispatchers: CoroutineDispatcher
    ): WeatherDataSource.Remote {
        return WeatherRemoteDataSource(weatherApiService, dispatchers)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherLocal: WeatherDataSource.Local,
        weatherRemote: WeatherDataSource.Remote,
    ) : WeatherRepository {
        return WeatherRepositoryImpl(weatherLocal, weatherRemote)
    }

    @Provides
    fun providesSearchWeatherByCityUseCase(weatherRepository: WeatherRepository): SearchWeatherByCityUseCase {
        return SearchWeatherByCityUseCase(weatherRepository)
    }

    @Provides
    fun provideGetWeatherByIdUseCase(weatherRepository: WeatherRepository): GetWeatherByIdUseCase {
        return GetWeatherByIdUseCase(weatherRepository)
    }

    @Provides
    fun providesSearchWeatherByCoordinatesUseCase(weatherRepository: WeatherRepository): SearchWeatherByCoordinatesUseCase {
        return SearchWeatherByCoordinatesUseCase(weatherRepository)
    }
}