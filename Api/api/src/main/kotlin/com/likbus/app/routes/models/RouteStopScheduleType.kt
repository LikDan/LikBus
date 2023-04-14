package com.likbus.app.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class RouteStopScheduleType(val type: String, val times: List<String>)
