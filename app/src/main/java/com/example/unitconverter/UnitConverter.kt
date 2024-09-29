package com.example.unitconverter

import androidx.lifecycle.ViewModel

class UnitConverter : ViewModel() {

    fun convertTemperature(value: Double, fromUnit: String, toUnit: String): Double {
        return when (fromUnit to toUnit) {
            "Celsius" to "Fahrenheit" -> value * 9 / 5 + 32
            "Fahrenheit" to "Celsius" -> (value - 32) * 5 / 9
            "Celsius" to "Kelvin" -> value + 273.15
            "Kelvin" to "Celsius" -> value - 273.15
            // Add more conversions as needed
            else -> value // No conversion needed if units are the same
        }
    }

    fun convertLength(value: Double, fromUnit: String, toUnit: String): Double {
        return when (fromUnit to toUnit) {
            "Feet" to "Meter" -> value * 0.3048
            "Meter" to "Feet" -> value * 3.28084
            "Mile" to "Kilometer" -> value * 1.60934
            "Kilometer" to "Mile" -> value * 0.621371
            "Inch" to "Centimeter" -> value * 2.54
            "Centimeter" to "Inch" -> value * 0.393701
            // Add more conversions as needed
            else -> value // No conversion needed if units are the same
        }
    }
}