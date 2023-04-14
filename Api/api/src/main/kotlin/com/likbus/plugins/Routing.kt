package com.likbus.plugins

import com.likbus.app.buses.routes.buses
import com.likbus.app.internalization.routes.internalization
import com.likbus.app.routes.routes.routes
import com.likbus.app.stops.routes.stops
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        faker()
        route("api") {
            stops()
            buses()
            routes()
            internalization()
        }
    }
}
