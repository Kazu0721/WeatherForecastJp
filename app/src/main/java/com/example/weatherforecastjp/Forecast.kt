package com.example.weatherforecastjp


data class ForecastItems(
    val date: String,
    val description: String,
    val temp: Double,
    val humidity: Int,
    val pressure: Int,
    val icon: String
)


