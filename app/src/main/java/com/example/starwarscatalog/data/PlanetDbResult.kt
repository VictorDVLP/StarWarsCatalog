package com.example.starwarscatalog.data

import java.io.Serializable

data class PlanetDbResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PlanetDb>
) : Serializable