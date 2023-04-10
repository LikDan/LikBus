package com.likbus.app.buses.repository.tables

import org.jetbrains.exposed.dao.id.UUIDTable


object BusTable : UUIDTable("buses") {
    val number = varchar("number", 10)
}
