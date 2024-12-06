package ru.km.weather.client.entity

data class City(
    val extId: Long,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: Int,
)