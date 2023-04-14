package com.likbus.app.buses.models

import kotlinx.serialization.Serializable

@Serializable
data class Bus(
    val id: String,
    val number: String,
    val start: String,
    val end: String,
    val startLng: Float,
    val startLat: Float,
    val endLng: Float,
    val endLat: Float,
    val type: String
)
