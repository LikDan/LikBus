package com.likbus.app.buses.repository

import com.likbus.app.buses.models.Bus
import com.likbus.app.buses.repository.tables.BusTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class BusEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    var number: String by BusTable.number
    var start: String by BusTable.start
    var end: String by BusTable.end
    var startLng: Float by BusTable.startLng
    var startLat: Float by BusTable.startLat
    var endLng: Float by BusTable.endLng
    var endLat: Float by BusTable.endLat
    var type: String by BusTable.type

    fun dto(): Bus = Bus(id.toString(), number, start, end, startLng, startLat, endLng, endLat, type)

    companion object : UUIDEntityClass<BusEntity>(BusTable)
}