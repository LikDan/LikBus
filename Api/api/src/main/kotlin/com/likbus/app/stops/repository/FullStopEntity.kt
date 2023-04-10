package com.likbus.app.stops.repository

import com.likbus.app.buses.models.Bus
import com.likbus.app.stops.models.Stop

data class FullStopEntity(val stop: Stop, val bus: Bus)
