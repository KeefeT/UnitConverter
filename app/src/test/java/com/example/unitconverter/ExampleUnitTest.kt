package com.example.unitconverter

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val unitConverter = UnitConverter()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCelsiusToFahrenheit() {
        val result = unitConverter.convertTemperature(0.0, "Celsius", "Fahrenheit")
        assertEquals(32.0, result, 0.001)
    }

    @Test
    fun testFahrenheitToCelsius() {
        val result = unitConverter.convertTemperature(32.0, "Fahrenheit", "Celsius")
        assertEquals(0.0, result, 0.001)
    }

    @Test
    fun testCelsiusToKelvin() {
        val result = unitConverter.convertTemperature(0.0, "Celsius", "Kelvin")
        assertEquals(273.15, result, 0.001)
    }

    @Test
    fun testFeetToMeter() {
        val result = unitConverter.convertLength(12.0, "Feet", "Meter")
        assertEquals(3.6576, result, 0.001)
    }

    @Test
    fun testMeterToFeet() {
        val result = unitConverter.convertLength(3.6576, "Meter", "Feet")
        assertEquals(12.0, result, 0.001)
    }

    @Test
    fun testMileToKilometer() {
        val result = unitConverter.convertLength(4.0, "Mile", "Kilometer")
        assertEquals(6.43738, result, 0.001)
    }

    @Test
    fun testKilometerToMile() {
        val result = unitConverter.convertLength(6.43738, "Kilometer", "Mile")
        assertEquals(4.0, result, 0.001)
    }

    @Test
    fun tesInchToCentimeter() {
        val result = unitConverter.convertLength(24.0, "Inch", "Centimeter")
        assertEquals(60.96, result, 0.001)
    }

    @Test
    fun testCentimeterToInch() {
        val result = unitConverter.convertLength(60.96, "Centimeter", "Inch")
        assertEquals(24.0, result, 0.001)
    }
}