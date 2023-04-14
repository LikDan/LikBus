package com.likbus.app.routes.models

import com.likbus.app.buses.models.Bus
import com.likbus.app.stops.models.Stop
import kotlinx.serialization.Serializable

@Serializable
data class RouteStopSchedule(val stop: Stop, val bus: Bus, val types: List<RouteStopScheduleType>)
