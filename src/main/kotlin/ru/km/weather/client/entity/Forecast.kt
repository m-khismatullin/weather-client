package ru.km.weather.client.entity

import ru.km.weather.client.util.Util
import java.time.ZonedDateTime

data class Forecast(
    val id: Long,
    val unixTime: Long,
    val city: City,
    val dataList: List<Weather>,
) {
    val forecastDate: ZonedDateTime
        get() = Util.unixTimeToZoned(unixTime, city.timezone)

    fun print() {
        println(
            "id=$id forecastDate=${
                with(this.forecastDate) {
                    "${this.toLocalDate()} ${this.hour}:${this.minute}"
                }
            }\n${this.city.name}"
        )
        this.dataList
            .map {
                "\t${it.weatherDate.toLocalDate()} ${
                    it.weatherDate.hour.toString().padStart(2, '0')
                } : ${it.data.temperature} : ${it.data.description}"
            }
            .forEach { println(it) }
    }
}