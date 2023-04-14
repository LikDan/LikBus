package com.likbus.app.routes.repository

import com.likbus.app.buses.models.Bus
import com.likbus.app.stops.models.Stop

data class RouteStopsEntity(val stop: Stop, val bus: Bus, val time: String, val type: String)
