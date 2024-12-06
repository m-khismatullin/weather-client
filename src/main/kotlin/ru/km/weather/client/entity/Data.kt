package ru.km.weather.client.entity

import java.time.ZonedDateTime

data class Data(
    val unixTime: Long,
    val temperature: Double,
    val feelsLike: Double,
    val pressure: Double,
    val humidity: Double,
    val description: String,
)