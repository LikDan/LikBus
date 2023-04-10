package com.likbus.app.stops.repository.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object StopTable : UUIDTable("stops") {
    val title = varchar("title", 128)
    val lng = float("lng")
    val lat = float("lat")
}
