package com.likbus.app.internalization.routes

import com.likbus.app.internalization.repository.InternalizationSet
import com.likbus.app.internalization.repository.InternalizationSet.Companion.get
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.internalization() {
    get("/internalization") {
        val locale = this.context.request.queryParameters["locale"]
        val entries: Map<String, String> = transaction {
            InternalizationSet.all().get(locale)
        }
        call.respond(entries)
    }
}
