package com.demo.data.repository

import android.content.Context
import androidx.room.Room
import com.demo.data.local.db.AppDataBase
import com.demo.data.local.resource.LocalWeatherRepositoryImp
import com.demo.data.remote.network.ApiService
import com.demo.data.remote.resource.RemoteWeatherRepositoryImp
import com.demo.domain.model.Weather
import com.demo.vdemotask.core.common.Resource
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import kotlin.test.assertEquals

class RemoteWeatherRepositoryImpTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var weatherRepository: WeatherRepositoryImp
    private lateinit var context: Context

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        context = Mockito.mock(Context::class.java)


        val apiService = retrofit.create(ApiService::class.java)
        val db = Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "database"
        ).allowMainThreadQueries().build()
        var dao = db.serviceDao()
        val remoteRepository = RemoteWeatherRepositoryImp(apiService)
        val localRepository = LocalWeatherRepositoryImp(dao)

        weatherRepository = WeatherRepositoryImp(localRepository, remoteRepository)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getWeather should return list of weather`() {
        // Given
        /* val mockWeather =ApiResponse(coord = Coord(21.12,21.12),
             weather = listOf(), base = "", main = Main(12.12,15.12,12.56,12.54,54,12L,45L,4L),
             54L, Wind(54.45,45L,45345.45), Rain(121.12),
             Clouds(45L),54L, Sys(2L,45L,"Cairo",54L,45L),
             545L,54L,"",54L

         )*/
        val mockWeather = Weather("Cairo", 45.165, "Cold", "Cold Weather","545",121.21,54.54)
        val mockApiResponse = listOf(mockWeather)
        val mockResponseBody = Gson().toJson(mockApiResponse)
        mockWebServer.enqueue(
            MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(mockResponseBody)
        )

        // When
        val flow = weatherRepository.getWeatherHistoryFromLocal()

        // Then
        runBlocking {
            flow.collect {
                when(it){
                    is Resource.Error -> {

                    }
                    is Resource.InitScreen -> {

                    }
                    Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        assertEquals(1, it.data.size)
                        assertEquals("Cairo", it.data.get(0).name)
                        assertEquals(45.165, it.data.get(0).temperature)
                        assertEquals("Cold", it.data.get(0).condition)
                        assertEquals("Cold Weather", it.data.get(0).description)
                    }
                }

            }
        }
    }
}
