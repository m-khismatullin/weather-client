package ru.km.weather.client.entity

import ru.km.weather.client.util.Util
import java.time.ZonedDateTime

data class Weather(
    val id: Long,
    val city: City,
    val data: Data,
) {
    val weatherDate: ZonedDateTime
        get() = Util.unixTimeToZoned(data.unixTime, city.timezone)

    fun print() {
        println(
            "id=$id : ${this.city.name} : ${
                with(this.weatherDate) {
                    "${this.toLocalDate()} ${this.hour}:${this.minute}"
                }
            } : ${
                with(this.data) {
                    "$description : temperature=$temperature : pressure=${pressure}hPa : humidity=${humidity}%"
                }
            }")
    }
}