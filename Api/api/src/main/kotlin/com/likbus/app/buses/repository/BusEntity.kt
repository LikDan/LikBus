package com.likbus.app.buses.repository

import com.likbus.app.buses.models.Bus
import com.likbus.app.buses.repository.tables.BusTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class BusEntity(id: EntityID<UUID>): UUIDEntity(id) {
    var number: String by BusTable.number

    fun dto(): Bus = Bus(id.toString(), number)

    companion object : UUIDEntityClass<BusEntity>(BusTable)
}