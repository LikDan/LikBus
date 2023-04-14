package com.likbus.app.routes.repository.tables

import com.likbus.app.buses.repository.tables.BusTable
import com.likbus.app.routes.repository.RouteStopsEntity
import com.likbus.app.stops.repository.tables.FullStopTable.fullStop
import com.likbus.app.stops.repository.tables.StopTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object RouteStopsTable : Table("route_stops") {
    val stop = reference("stop_id", StopTable, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    val bus = reference("bus_id", BusTable, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    val time = long("time")
    val type = varchar("type", 9)

    private fun normalizeTime(time: Long): String {
        return if (time < 10) "0${time}" else "$time"
    }

    fun ResultRow.routeStopTime(): RouteStopsEntity {
        val fullStop = this.fullStop()
        val time = this[time]
        val timeStr = "${normalizeTime(time / 60)}:${normalizeTime(time % 60)}:00"
        return RouteStopsEntity(stop = fullStop.stop, bus = fullStop.bus, time = timeStr, type = this[type])
    }
}
