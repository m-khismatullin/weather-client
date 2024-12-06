package ru.km.weather.client

import io.smallrye.common.annotation.Blocking
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.reactive.messaging.Incoming
import ru.km.weather.client.entity.Forecast
import ru.km.weather.client.entity.Weather

@ApplicationScoped
class Processor {
    @Incoming("forecast")
    @Blocking
    fun processForecast(forecast: Forecast) {
        forecast.print()
    }

    @Incoming("weather")
    @Blocking
    fun processWeather(weather: Weather) {
        weather.print()
    }
}