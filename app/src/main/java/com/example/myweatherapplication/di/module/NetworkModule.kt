package com.example.myweatherapplication.di.module

import com.example.myweatherapplication.data.Constants
import com.example.myweatherapplication.data.Constants.Companion.BASE_URL
import com.example.myweatherapplication.data.weather.remote.api.WeatherApiService
import com.example.myweatherapplication.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideTokenInterceptor(): Interceptor = Interceptor { chain ->
        val original: Request = chain.request()
        val originalUrl = original.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter(Constants.API_ID, BuildConfig.API_KEY)
            .build()

        val requestBuilder = original.newBuilder().url(newUrl)
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(headerInterceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    }

    @Singleton
    @Provides
    fun provideRetrofitWeatherService(okHttpClient: OkHttpClient): WeatherApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WeatherApiService::class.java)
    }
}