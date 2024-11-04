package com.demo.data.di

import com.demo.data.repository.LocalRepository
import com.demo.data.repository.RemoteRepository
import com.demo.data.repository.WeatherRepositoryImp
import com.demo.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalRepository,
        remoteDataSource: RemoteRepository
    ): WeatherRepository {
        return WeatherRepositoryImp(
            localDataSource, remoteDataSource
        )
    }


}