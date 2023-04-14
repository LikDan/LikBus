package com.likbus.app.internalization.repository.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column


object InternalizationTable : IdTable<String>("internalization") {
    override var id: Column<EntityID<String>> = varchar("key", 255).uniqueIndex().entityId()
    val enUS = varchar("en-US", 255)
    val ruRU = varchar("ru-RU", 255)
}
