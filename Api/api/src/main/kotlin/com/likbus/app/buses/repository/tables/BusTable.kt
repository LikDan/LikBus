package com.likbus.app.buses.repository.tables

import org.jetbrains.exposed.dao.id.UUIDTable


object BusTable : UUIDTable("buses") {
    val number = varchar("number", 10)
    val start = varchar("start", 255)
    val end = varchar("end", 255)
    val startLng = float("startLng")
    val startLat = float("startLat")
    val endLng = float("endLng")
    val endLat = float("endLat")
    val type = varchar("type", 20)
}
