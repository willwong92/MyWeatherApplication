package com.example.myweatherapplication.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    @Named("IO")
    fun provideBackgroundDispatchers(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    @Named("MAIN")
    fun provideMainDispatchers(): CoroutineDispatcher = Dispatchers.Main
}