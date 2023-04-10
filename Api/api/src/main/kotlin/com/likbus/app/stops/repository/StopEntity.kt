package com.likbus.app.stops.repository

import com.likbus.app.stops.models.Stop
import com.likbus.app.stops.repository.tables.StopTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class StopEntity(id: EntityID<UUID>): UUIDEntity(id) {
    var title: String by StopTable.title
    var lng: Float by StopTable.lng
    var lat: Float by StopTable.lat

    fun dto(): Stop = Stop(id.toString(), title, lng, lat)

    companion object : UUIDEntityClass<StopEntity>(StopTable)
}