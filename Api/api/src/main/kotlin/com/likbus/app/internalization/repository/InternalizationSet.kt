package com.likbus.app.internalization.repository

import com.likbus.app.internalization.repository.tables.InternalizationTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable

class InternalizationSet(id: EntityID<String>) : Entity<String>(id) {
    var enUS: String by InternalizationTable.enUS
    var ruRU: String by InternalizationTable.ruRU

    companion object : EntityClass<String, InternalizationSet>(InternalizationTable) {
        fun SizedIterable<InternalizationSet>.get(locale: String?) =
            associate {
                it.id.value to when (locale) {
                    "en-US" -> it.enUS
                    "ru-RU" -> it.ruRU
                    else -> it.enUS
                }
            }
    }
}
