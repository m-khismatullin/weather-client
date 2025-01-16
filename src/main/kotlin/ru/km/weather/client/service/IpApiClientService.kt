package ru.km.weather.client.service

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient
import ru.km.weather.client.dto.IpApiDataDto
import ru.km.weather.client.rest.IpApiClient

@ApplicationScoped
class IpApiClientService {
    @RestClient
    private lateinit var ipApiClient: IpApiClient

    suspend fun getIpGeolocationData(): IpApiDataDto = ipApiClient.getIpGeolocationData()
}