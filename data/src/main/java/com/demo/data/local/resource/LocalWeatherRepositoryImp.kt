package com.demo.data.local.resource

import com.demo.data.local.db.ServiceDao
import com.demo.data.model.WeatherDataEntity
import com.demo.data.repository.LocalRepository
import javax.inject.Inject

class LocalWeatherRepositoryImp @Inject constructor(val serviceDao: ServiceDao) :LocalRepository {
    override suspend fun addWeatherItem(post: WeatherDataEntity): Long {
        return serviceDao.addWeatherItem(post)
    }

    override suspend fun getAllWeatherHistory(): List<WeatherDataEntity> {
        return serviceDao.getAllWeatherHistory()
    }

    override suspend fun getCount(): Int {
        return serviceDao.getCount()
    }

    override suspend fun deleteFirstInRaw() {
        return serviceDao.deleteFirstInRaw()
    }

}