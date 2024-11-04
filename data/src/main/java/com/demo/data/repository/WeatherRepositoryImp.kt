package com.demo.data.repository

import android.annotation.SuppressLint
import android.content.SyncRequest
import com.demo.customweatherlibrary.Secrets
import com.demo.customweatherlibrary.factory.UnifiedSecrets
import com.demo.data.remote.mapper.toDomainFromLocal
import com.demo.data.remote.mapper.toLocalFromRemote
//import com.demo.data.remote.mapper.toDomainFromLocal
//import com.demo.data.remote.mapper.toLocalFromRemote
import com.demo.data.remote.network.ApiLocationResponse
import com.demo.domain.model.Weather
import com.demo.domain.repository.WeatherRepository
import com.demo.vdemotask.core.common.Resource
import com.demo.vdemotask.core.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : WeatherRepository {
    override fun searchWeatherForCity(city: String): Flow<Resource<Weather>> = flow {
        try {
            var remoteLocation = remoteRepository.getCityLocation(city, 1, UnifiedSecrets.getSecretApiKey())
            when(remoteLocation){
                is Resource.Error -> {
                    emit(Resource.Error(remoteLocation.exception))

                }
                is Resource.InitScreen -> TODO()
                Resource.Loading -> TODO()
                is Resource.Success -> {
                    if (remoteLocation.data.isNotEmpty()){
                    var data = remoteLocation.data[0]

                    var remoteResponse = remoteRepository.getWeatherByCity(
                        data.lat,
                        data.lon,
                        UnifiedSecrets.getSecretApiKey()
                    )
                    when (remoteResponse) {
                        is Resource.Error -> {
                            emit(Resource.Error(remoteResponse.exception))

                        }

                        is Resource.InitScreen -> TODO()
                        Resource.Loading -> TODO()
                        is Resource.Success -> {
                            if (localRepository.getCount() > 4)
                                localRepository.deleteFirstInRaw()

                            var insertResult =
                                localRepository.addWeatherItem(remoteResponse.data.toLocalFromRemote())
                            if (insertResult > 0)
                                emit(Resource.Success(remoteResponse.data.toDomainFromLocal()))
                            else
                                emit(Resource.Error(Exception("Error Insertion")))
                        }
                    }
                }else{
                        emit(Resource.Error(Exception("No City Found")))

                    }
                }
            }
        } catch (ex: Exception) {
            emit(Resource.Error(ex))
        }
    }

    override fun getWeatherHistoryFromLocal(): Flow<Resource<List<Weather>>> = flow {
        try {
            var localResult = localRepository.getAllWeatherHistory()
            if (localResult.isNotEmpty()){
                emit(Resource.Success(localResult.map { it.toDomainFromLocal() }))
            }else
                emit(Resource.Error(Exception("Empty List")))
        } catch (ex: Exception) {
            emit(Resource.Error(ex))
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun searchSevenDaysWeather(lat:String, lon:String): Flow<Resource<List<Weather>>> = flow{
        try {

            var remoteResponse = remoteRepository.getSevenDayWeather(
                        lat.toDouble(),
                        lon.toDouble(),3, UnifiedSecrets.getSecretApiKey()
                    )
                    when(remoteResponse){
                        is Resource.Error -> {
                            emit(Resource.Error(exception = remoteResponse.exception))
                        }
                        is Resource.InitScreen -> TODO()
                        Resource.Loading -> TODO()
                        is Resource.Success -> {
                            emit(Resource.Success(remoteResponse.data.map {  it.toDomainFromLocal()}))

                        }
                    }


        } catch (ex: Exception) {
            emit(Resource.Error(exception = ex))
        }
    }


}