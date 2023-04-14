package com.likbus.app.stops.repository.tables

import com.likbus.app.buses.models.Bus
import com.likbus.app.buses.repository.tables.BusTable
import com.likbus.app.stops.models.Stop
import com.likbus.app.stops.repository.FullStopEntity
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object FullStopTable : Table("fullstops") {
    val stop = reference("stop_id", StopTable, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    val bus = reference("bus_id", BusTable, ReferenceOption.CASCADE, ReferenceOption.CASCADE)

    fun ResultRow.fullStop(): FullStopEntity {
        val stop = Stop(
            id = this[StopTable.id].toString(),
            title = this[StopTable.title],
            lng = this[StopTable.lng],
            lat = this[StopTable.lat],
        )

        val bus = Bus(
            id = this[BusTable.id].toString(),
            number = this[BusTable.number],
            start = this[BusTable.start],
            end = this[BusTable.end],
            startLng = this[BusTable.startLng],
            startLat = this[BusTable.startLat],
            endLng = this[BusTable.endLng],
            endLat = this[BusTable.endLat],
            type = this[BusTable.type]
        )

        return FullStopEntity(stop, bus)
    }
}
