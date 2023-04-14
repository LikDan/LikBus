package com.likbus.app.routes.routes

import com.likbus.app.buses.repository.tables.BusTable
import com.likbus.app.routes.models.RouteStopSchedule
import com.likbus.app.routes.models.RouteStopScheduleType
import com.likbus.app.routes.repository.tables.RouteStopsTable
import com.likbus.app.routes.repository.tables.RouteStopsTable.routeStopTime
import com.likbus.app.stops.repository.tables.StopTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun Route.routes() {
    get("/stops/{stopID}/buses/{busID}/schedule") {
        val stopID = runCatching { UUID.fromString(call.parameters["stopID"]) }.getOrNull() ?: return@get call.respond(
            HttpStatusCode.UnprocessableEntity
        )
        val busID = runCatching { UUID.fromString(call.parameters["busID"]) }.getOrNull() ?: return@get call.respond(
            HttpStatusCode.UnprocessableEntity
        )
        val routeStopRaw = transaction {
            (RouteStopsTable innerJoin BusTable innerJoin StopTable).select {
                (RouteStopsTable.stop eq stopID) and (RouteStopsTable.bus eq busID)
            }.orderBy(RouteStopsTable.time).map { it.routeStopTime() }
        }

        if (routeStopRaw.isEmpty()) return@get call.respond(HttpStatusCode.UnprocessableEntity)

        val routeStopSchedule = RouteStopSchedule(
            stop = routeStopRaw.first().stop,
            bus = routeStopRaw.first().bus,
            types = routeStopRaw
                .groupBy { it.type }
                .map { RouteStopScheduleType(it.key, it.value.map { v -> v.time }) }
        )

        call.respond(routeStopSchedule)
    }
}