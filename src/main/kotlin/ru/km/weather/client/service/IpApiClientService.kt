package ru.km.weather.client.service

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient
import ru.km.weather.client.dto.IpApiDataDto
import ru.km.weather.client.rest.IpApiClient

@ApplicationScoped
class IpApiClientService {
    @RestClient
    private lateinit var ipApiClient: IpApiClient

    fun getIpGeolocationData(): Uni<IpApiDataDto> = ipApiClient.getIpGeolocationData()
}