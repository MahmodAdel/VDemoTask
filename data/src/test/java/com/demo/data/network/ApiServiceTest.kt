package com.demo.data.network

import com.demo.data.remote.network.ApiResponse
import com.demo.data.remote.network.ApiService
import com.demo.data.remote.network.Clouds
import com.demo.data.remote.network.Coord
import com.demo.data.remote.network.Main
import com.demo.data.remote.network.Rain
import com.demo.data.remote.network.Sys
import com.demo.data.remote.network.Wind
import com.demo.domain.model.Weather
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Unit test class for ApiService
class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        // Start the MockWebServer
        mockWebServer = MockWebServer()
        mockWebServer.start()

        // Initialize Retrofit with MockWebServer base URL
        val retrofit = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).build()

        // Create an instance of ApiService using Retrofit
        apiService = retrofit.create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        // Shutdown the MockWebServer after the test
        mockWebServer.shutdown()
    }

    @Test
    fun testGetFixturesAPICall(): Unit = runBlocking {
        // Test code

        // Prepare mock response data
         val mockApiResponse = ApiResponse(coord = Coord(21.12,21.12),
             weather = listOf(), base = "", main = Main(12.12,15.12,12.56,12.54,54,12L,45L,4L),
             54L, Wind(54.45,45L,45345.45), Rain(121.12),
             Clouds(45L),54L, Sys(2L,45L,"Cairo",54L,45L),
             545L,54L,"",54L

         )
         //val mockApiResponse = ApiResponseTest(weatherList = listOf(mockFixture))
         val mockResponseBody = Gson().toJson(mockApiResponse)

         // Enqueue the mock response
         val mockResponse = MockResponse().setResponseCode(200).setBody(mockResponseBody)
         mockWebServer.enqueue(mockResponse)

         // Call the API method
         val apiResponse = apiService.getWeatherByCity(62.2655,45.154,"API_KEY")

         // Print the response body for debugging
         println(apiResponse)

         assertNotNull(apiResponse)

         // Access properties of the response body only if it's not null
         apiResponse.body()?.let { weather ->
             // Assert that the fixture data matches the expected values
             assertEquals("Cairo", weather.sys.country)
             assertEquals(12.12, weather.main.temp)
             assertEquals(21.12, weather.coord.lat)
             assertEquals(21.12, weather.coord.lon)

         }
    }
}
