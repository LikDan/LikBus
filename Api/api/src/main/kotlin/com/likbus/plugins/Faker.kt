package com.likbus.plugins

import com.likbus.app.internalization.repository.tables.InternalizationTable
import com.likbus.app.stops.repository.StopEntity
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import io.ktor.server.routing.*
import org.apache.commons.lang3.StringEscapeUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun asciiToString() {

}


fun translate(input: String): String {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://translated-mymemory---translation-memory.p.rapidapi.com/get?langpair=en%7Cru&q=${input}&key=707afd9f714cbfdb619a&mt=1&onlyprivate=0&de=a%40b.c")
        .get()
        .addHeader("X-RapidAPI-Key", "897516a60emshfda20740717761ep18f483jsnd4b81be9a646")
        .addHeader("X-RapidAPI-Host", "translated-mymemory---translation-memory.p.rapidapi.com")
        .build()

    val response = client.newCall(request).execute().body().string().substringAfter("{\"responseData\":{\"translatedText\":\"").substringBefore("\"")
    return StringEscapeUtils.unescapeJava(response)
}

fun Route.faker() = post ("faker") {
    transaction {
        StopEntity.all().distinctBy { it.title }.map { it.title }.map {s ->
            val en = s.lowercase().split("_").joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
            Triple(s, en, "Undefined")
        }.forEach {t ->
            InternalizationTable.insert {
                it[this.id] = t.first
                it[this.enUS] = t.second
                it[this.ruRU] = t.third
            }
        }
    }


//    stops.map { s ->
//        InternalizationTable.insert {
//            it[this.id] = s
//            it[this.enUS] =
//            it[this.ruRU] = faker.
//        }
//    }



/*    val faker = Faker()

    transaction {
        for (i in 0..50)
            StopEntity.new {
                this.title = faker.address.streetName().replace(" ", "_").uppercase()
                this.lat = Random.nextDouble(-90.0, 90.0).toFloat()
                this.lng = Random.nextDouble(-180.0, 180.0).toFloat()
            }

        val types = listOf("BUS","BUS","BUS", "TRAM", "TRAM", "TROLLEYBUS", "TROLLEYBUS", "METRO", "SUBURBAN_BUS", "SUBURBAN_TRAM", "SUBURBAN_TROLLEYBUS")

        for (i in 0..1000)
            BusEntity.new {
                this.number = faker.address.buildingNumber()
                this.start = faker.address.streetName().replace(" ", "_").uppercase()
                this.end = faker.address.streetName().replace(" ", "_").uppercase()
                this.startLng = Random.nextDouble(-180.0, 180.0).toFloat()
                this.startLat = Random.nextDouble(-90.0, 90.0).toFloat()
                this.endLng = Random.nextDouble(-180.0, 180.0).toFloat()
                this.endLat = Random.nextDouble(-90.0, 90.0).toFloat()
                this.type = types.random()
            }


        val buses = BusEntity.all().distinctBy { it.id }.map { it.id }
        val stops = StopEntity.all().distinctBy { it.id }.map { it.id }

        for (i in 0..20000)
            FullStopTable.insert {
                val busID = buses.random()
                val stopID = stops.random()
                it[this.bus] = busID
                it[this.stop] = stopID
            }


        val weekdays = listOf("WEEKDAYS","WEEKDAYS","WEEKDAYS", "WEEKENDS", "WEEKENDS")

        for (i in 0..1000000)
            RouteStopsTable.insert {
                val busID = buses.random()
                val stopID = stops.random()
                it[this.bus] = busID
                it[this.stop] = stopID
                it[this.time] = Random.nextLong(0, 1440)
                it[this.type] = weekdays.random()
            }
        }*/
}