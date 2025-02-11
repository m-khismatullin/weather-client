package ru.km.weather.client.rest

import io.smallrye.mutiny.Uni
import jakarta.ws.rs.POST
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import ru.km.weather.client.dto.SubscriberDto

@RegisterRestClient(configKey = "weather-subscription-api")
interface WeatherSubscriberClient {
    @POST
    fun addSubscribe(subscriberDto: SubscriberDto): Uni<String>
}