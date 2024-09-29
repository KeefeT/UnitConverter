package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            UnitConverterApp()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        Greeting("Android")
    }
}

@Composable
fun UnitConverterApp() {
    val viewModel = UnitConverter()

    var inputValue by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Temperature") }
    var fromUnit by remember { mutableStateOf("Celsius") }
    var toUnit by remember { mutableStateOf("Fahrenheit") }
    var result by remember { mutableDoubleStateOf(0.0) }

    val categories = listOf("Temperature", "Length", "Weight")
    val unitsMap = mapOf(
        "Temperature" to listOf("Celsius", "Fahrenheit", "Kelvin"),
        "Length" to listOf("Meters", "Kilometers", "Miles"),
        "Weight" to listOf("Kilograms", "Pounds", "Grams")
    )

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Enter Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        DropdownMenu(selectedCategory, categories, onSelectionChanged = { category ->
            selectedCategory = category
            val units = unitsMap[category] ?: emptyList()
            fromUnit = units.first()
            toUnit = units.last()
        })

        DropdownMenu(fromUnit, unitsMap[selectedCategory] ?: emptyList()) { fromUnit = it }
        DropdownMenu(toUnit, unitsMap[selectedCategory] ?: emptyList()) { toUnit = it }

        Button(onClick = {
            val value = inputValue.toDoubleOrNull() ?: 0.0
            result = when (selectedCategory) {
                "Temperature" -> viewModel.convertTemperature(value, fromUnit, toUnit)
                "Length" -> viewModel.convertLength(value, fromUnit, toUnit)
                else -> 0.0
            }
        }) {
            Text("Convert")
        }

        Text(text = "Result: $result")
    }
}

@Composable
fun DropdownMenu(selectedItem: String, items: List<String>, onSelectionChanged: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedItem)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {Text(item)},
                    onClick = {
                        expanded = false
                        onSelectionChanged(item)
                    }
                )
            }
        }
    }
}
