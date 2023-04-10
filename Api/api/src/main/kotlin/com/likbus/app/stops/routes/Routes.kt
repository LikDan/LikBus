package com.likbus.app.stops.routes

import com.likbus.app.buses.repository.tables.BusTable
import com.likbus.app.stops.models.FullStop
import com.likbus.app.stops.models.Stop
import com.likbus.app.stops.repository.StopEntity
import com.likbus.app.stops.repository.tables.FullStopTable
import com.likbus.app.stops.repository.tables.FullStopTable.fullStop
import com.likbus.app.stops.repository.tables.StopTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

fun Route.stops() {
    get("/stops") {
        val stops: List<Stop> = transaction {
            StopEntity.all().map { it.dto() }
        }
        call.respond(stops)
    }

    get("/stops/{id}/buses") {
        val id = runCatching {  UUID.fromString(call.parameters["id"]) }.getOrNull() ?: return@get call.respond(HttpStatusCode.UnprocessableEntity)
        val stopsRaw = transaction {
            (FullStopTable innerJoin BusTable innerJoin StopTable).select {
                FullStopTable.stop eq id
            }.map { it.fullStop() }
        }

        if (stopsRaw.isEmpty()) return@get call.respond(HttpStatusCode.UnprocessableEntity)

        val stop = FullStop(
            stop = stopsRaw.first().stop,
            buses = stopsRaw.map { it.bus }
        )

        call.respond(stop)
    }

    post("/stops") {
        val stop = transaction {
            StopEntity.new {
                title = "A"
                lng = 0.5f
                lat = 10f
            }
        }.dto()

        call.respond(stop)
    }
}