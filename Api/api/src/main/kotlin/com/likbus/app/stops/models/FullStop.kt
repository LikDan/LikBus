package com.likbus.app.stops.models

import com.likbus.app.buses.models.Bus
import kotlinx.serialization.Serializable

@Serializable
data class FullStop(val stop: Stop, val buses: List<Bus>)
