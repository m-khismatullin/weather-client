package ru.km.weather.client.rest

import jakarta.ws.rs.GET
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import ru.km.weather.client.dto.IpApiDataDto

@RegisterRestClient(configKey = "ip-api")
interface IpApiClient {
    @GET
    suspend fun getIpGeolocationData(): IpApiDataDto
}
