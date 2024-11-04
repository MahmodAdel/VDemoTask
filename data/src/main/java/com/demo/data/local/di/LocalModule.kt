package com.demo.data.local.di

import android.content.Context
import androidx.room.Room
import com.demo.data.local.db.AppDataBase
import com.demo.data.local.db.ServiceDao
import com.demo.data.local.resource.LocalWeatherRepositoryImp
import com.demo.data.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "database"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideYourDao(db: AppDataBase) = db.serviceDao()

    @Singleton
    @Provides
    fun provideLocalRepository(
        serviceDao: ServiceDao
    ): LocalRepository {
        return LocalWeatherRepositoryImp(
            serviceDao
        )
    }
}