package com.demo.domain.repository

import com.demo.domain.model.Weather
import com.demo.vdemotask.core.common.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    private val sampleWeather = listOf(
        Weather("Cairo", 54.15, "Cold", "Cold Weather","",45.12,54.15),
        Weather("Alex", 54.15, "Hot", "Hot Weather","",45.12,54.15)
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getFixtures returns correct data`() = runBlockingTest {
        `when`(weatherRepository.getWeatherHistoryFromLocal()).thenReturn(flowOf(Resource.Success(sampleWeather)))

        val result: Flow<Resource<List<Weather>>> = weatherRepository.getWeatherHistoryFromLocal()
        result.collect { weather ->
            when(weather){
                is Resource.Error -> {

                }
                is Resource.InitScreen -> TODO()
                Resource.Loading -> TODO()
                is Resource.Success -> {
                    assertEquals(2, weather.data.size)

                }
            }

        }

        verify(weatherRepository, times(1)).getWeatherHistoryFromLocal()
    }
}
