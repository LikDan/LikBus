package com.likbus.app.internalization.models

import kotlinx.serialization.Serializable

@Serializable
data class InternalizationEntry(
    val key: String,
    val value: String,
)
