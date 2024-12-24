package ru.km.weather.client.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class IpApiDataDto(
    val query: String?,
    val status: String?,
    val message: String?,
    val continent: String?,
    val continentCode: String?,
    val country: String?,
    val countryCode: String?,
    val region: String?,
    val regionName: String?,
    val city: String?,
    val district: String?,
    val zip: String?,
    val lat: Double,
    val lon: Double,
    val timezone: String?,
    val offset: Long?,
    val currency: String?,
    val isp: String?,
    val org: String?,
    val `as`: String?,
    val asname: String?,
    val reverse: String?,
    val mobile: Boolean?,
    val proxy: Boolean?,
    val hosting: Boolean?,
)
