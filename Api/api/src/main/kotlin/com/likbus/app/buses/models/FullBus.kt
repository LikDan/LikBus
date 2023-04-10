package com.likbus.app.buses.models

import com.likbus.app.stops.models.Stop
import kotlinx.serialization.Serializable

@Serializable
data class FullBus(val bus: Bus, val stops: List<Stop>)
