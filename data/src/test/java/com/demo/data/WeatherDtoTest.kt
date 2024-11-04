package com.demo.data

import com.demo.data.model.WeatherDto
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherDtoTest {

    @Test
    fun `test WeatherDto properties`() {
        // Create an instance of FixtureDto
        val weather = WeatherDto(
            id = 1,
            name = "Cairo",
            condition = "Cloud",
            temperature = 12.12
        )

        // Verify the properties
        assertEquals(1, weather.id)
        assertEquals("Test Fixture", weather.name)
        assertEquals("Cloud", weather.condition)
        assertEquals(12.12, weather.temperature)
    }

    @Test
    fun `test WeatherDto equality`() {
        // Create two instances of FixtureDto with the same properties
        val weather1 = WeatherDto(
            id = 1,
            name = "Test Fixture",
            condition = "Cloud",
            temperature = 12.12
        )

        val weather2 = WeatherDto(
            id = 1,
            name = "Test Fixture",
            condition = "Cloud",
            temperature = 12.12
        )

        // Verify that they are equal
        assertEquals(weather1, weather2)
    }

    @Test
    fun `test WeatherDto copy`() {
        // Create an instance of FixtureDto
        val fixture = WeatherDto(
            id = 1,
            name = "Test Fixture",
            condition = "Cloud",
            temperature = 12.12
        )

        // Create a copy with a modified property
        val modifiedFixture = fixture.copy(name = "Modified Weather")

        // Verify the properties
        assertEquals(1, modifiedFixture.id)
        assertEquals("Modified Weather", modifiedFixture.name)
        assertEquals("Cloud", modifiedFixture.condition)
        assertEquals(12.12, modifiedFixture.temperature)
    }
}
