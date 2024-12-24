package ru.km.weather.client.rest

import io.smallrye.mutiny.Uni
import jakarta.ws.rs.GET
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import ru.km.weather.client.dto.IpApiDataDto

@RegisterRestClient(configKey = "ip-api")
interface IpApiClient {
    @GET
    fun getIpGeolocationData(): Uni<IpApiDataDto>
}
