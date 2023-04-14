package com.likbus.app.buses.routes

import com.likbus.app.buses.models.Bus
import com.likbus.app.buses.models.FullBus
import com.likbus.app.buses.repository.BusEntity
import com.likbus.app.buses.repository.tables.BusTable
import com.likbus.app.stops.repository.tables.FullStopTable
import com.likbus.app.stops.repository.tables.FullStopTable.fullStop
import com.likbus.app.stops.repository.tables.StopTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun Route.buses() {
    get("/buses/types") {
        val string: List<String> = transaction {
            BusEntity.all().distinctBy { it.type }.map { it.type }.sorted()
        }
        call.respond(string)
    }

    get("/buses") {
        val type = this.context.request.queryParameters["type"]?.uppercase()
        val buses: List<Bus> = transaction {
            (if (type.isNullOrEmpty()) BusEntity.all() else BusEntity.find { BusTable.type eq type }).map { it.dto() }
        }
        call.respond(buses)
    }

    get("/buses/{id}/stops") {
        val id = runCatching {  UUID.fromString(call.parameters["id"]) }.getOrNull() ?: return@get call.respond(HttpStatusCode.UnprocessableEntity)
        val busesRaw = transaction {
            (FullStopTable innerJoin BusTable innerJoin StopTable).select {
                FullStopTable.bus eq id
            }.map { it.fullStop() }
        }

        if (busesRaw.isEmpty()) return@get call.respond(HttpStatusCode.UnprocessableEntity)

        val stop = FullBus(
            stops = busesRaw.map { it.stop },
            bus = busesRaw.first().bus
        )

        call.respond(stop)
    }

    post("/buses") {
        val bus = transaction {
            BusEntity.new {
                number = "Number#1"
            }
        }.dto()

        call.respond(bus)
    }
}