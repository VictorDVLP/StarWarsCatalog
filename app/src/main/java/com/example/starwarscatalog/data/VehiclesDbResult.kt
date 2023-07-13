package com.example.starwarscatalog.data

import java.io.Serializable

data class VehiclesDbResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<VehiclesDb>
): Serializable