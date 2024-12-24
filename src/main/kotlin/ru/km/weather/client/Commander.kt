package ru.km.weather.client

import io.quarkus.logging.Log
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import jakarta.inject.Inject
import org.eclipse.microprofile.rest.client.inject.RestClient
import ru.km.weather.client.dto.SubscriberDto
import ru.km.weather.client.rest.WeatherSubscriberClient
import ru.km.weather.client.service.IpApiClientService

@QuarkusMain
class Commander : QuarkusApplication {
    @Inject
    private lateinit var ipApiClientService: IpApiClientService

    @RestClient
    private lateinit var weatherSubscriberClient: WeatherSubscriberClient

    override fun run(vararg args: String?): Int {
        ipApiClientService.getIpGeolocationData()
            .onItem()
            .invoke { data ->
                Log.info(data)
            }
            .onItem()
            .transformToUni { data ->
                weatherSubscriberClient.addSubscribe(
                    SubscriberDto(
                        data.city ?: "unknown",
                        data.lat,
                        data.lon
                    )
                )
            }
            .subscribe()
            .with { Log.info(it) }

        weatherSubscriberClient
            .addSubscribe(
                SubscriberDto(
                    "Подымалово",
                    54.867,
                    55.750
                )
            )
            .subscribe()
            .with { Log.info(it) }

        Quarkus.waitForExit()

        return 0;
    }
}