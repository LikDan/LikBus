package com.likbus.app.stops.models

import kotlinx.serialization.Serializable

@Serializable
data class Stop(val id: String, val title: String, val lng: Float, val lat: Float)