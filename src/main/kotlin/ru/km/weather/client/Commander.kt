package ru.km.weather.client

import io.quarkus.logging.Log
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import jakarta.inject.Inject
import org.eclipse.microprofile.rest.client.inject.RestClient
import ru.km.weather.client.dto.CityBriefDto
import ru.km.weather.client.dto.SubscriberDto
import ru.km.weather.client.rest.WeatherSubscriberClient
import ru.km.weather.client.service.IpApiClientService

@QuarkusMain
class Commander : QuarkusApplication {
    companion object {
        val extraCityList = listOf(
            CityBriefDto(name = "Москва", lat = 55.75, lon = 37.62),
            CityBriefDto(name = "Калуга", lat = 54.53, lon = 36.28),
            CityBriefDto(name = "Адлер", lat = 43.43, lon = 39.92),
            CityBriefDto(name = "Пермь", lat = 58.01, lon = 56.25),
            CityBriefDto(name = "Анапа", lat = 44.89, lon = 37.32),
            CityBriefDto(name = "Пятигорск", lat = 44.05, lon = 43.06),
            CityBriefDto(name = "Рязань", lat = 54.63, lon = 39.69),
            CityBriefDto(name = "Самара", lat = 53.20, lon = 50.15),
            CityBriefDto(name = "Бердск", lat = 54.76, lon = 83.10),
            CityBriefDto(name = "Кумертау", lat = 52.77, lon = 55.78),
            CityBriefDto(name = "Магадан", lat = 59.56, lon = 150.80),
            CityBriefDto(name = "Донецк", lat = 48.34, lon = 39.96),
            CityBriefDto(name = "Сургут", lat = 61.25, lon = 73.42),
            CityBriefDto(name = "Белебей", lat = 54.12, lon = 54.12),
            CityBriefDto(name = "Санкт-Петербург", lat = 59.94, lon = 30.31),
            CityBriefDto(name = "Барнаул", lat = 53.36, lon = 83.76),
            CityBriefDto(name = "Саратов", lat = 51.54, lon = 46.01),
            CityBriefDto(name = "Сергиев Посад", lat = 56.30, lon = 38.13),
            CityBriefDto(name = "Курск", lat = 51.74, lon = 36.19),
            CityBriefDto(name = "Волгоград", lat = 48.72, lon = 44.50),
            CityBriefDto(name = "Владивосток", lat = 43.11, lon = 131.87),
            CityBriefDto(name = "Тольятти", lat = 53.53, lon = 49.35),
            CityBriefDto(name = "Нерюнгри", lat = 56.67, lon = 124.64),
            CityBriefDto(name = "Екатеринбург", lat = 56.85, lon = 60.61),
            CityBriefDto(name = "Калининград", lat = 54.71, lon = 20.51),
            CityBriefDto(name = "Чита", lat = 52.03, lon = 113.50),
            CityBriefDto(name = "Казань", lat = 55.79, lon = 49.12),
            CityBriefDto(name = "Сочи", lat = 43.60, lon = 39.73),
            CityBriefDto(name = "Краснодар", lat = 45.04, lon = 38.98),
            CityBriefDto(name = "Астрахань", lat = 46.35, lon = 48.04),
            CityBriefDto(name = "Псков", lat = 57.81, lon = 28.35),
            CityBriefDto(name = "Кемерово", lat = 55.33, lon = 86.08),
            CityBriefDto(name = "Петрозаводск", lat = 61.78, lon = 34.35),
            CityBriefDto(name = "Ростов-на-Дону", lat = 47.23, lon = 39.72),
            CityBriefDto(name = "Биробиджан", lat = 48.79, lon = 132.92),
            CityBriefDto(name = "Бугульма", lat = 54.54, lon = 52.80),
            CityBriefDto(name = "Ставрополь", lat = 45.04, lon = 41.97),
            CityBriefDto(name = "Сызрань", lat = 53.16, lon = 48.47),
            CityBriefDto(name = "Таганрог", lat = 47.24, lon = 38.90),
            CityBriefDto(name = "Тамбов", lat = 52.73, lon = 41.44),
            CityBriefDto(name = "Воронеж", lat = 51.67, lon = 39.18),
            CityBriefDto(name = "Мурманск", lat = 68.98, lon = 33.09),
        )
    }

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

//        weatherSubscriberClient
//            .addSubscribe(
//                SubscriberDto(
//                    "Подымалово",
//                    54.867,
//                    55.750
//                )
//            )
//            .subscribe()
//            .with { Log.info(it) }

//        extraCityList.forEach {
//            weatherSubscriberClient
//                .addSubscribe(
//                    SubscriberDto(
//                        it.name,
//                        it.lat,
//                        it.lon
//                    )
//                )
//                .subscribe()
//                .with { Log.info(it) }
//        }

        Quarkus.waitForExit()

        return 0;
    }
}