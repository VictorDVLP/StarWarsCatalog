package com.example.starwarscatalog.data

data class StarshipDbResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<StarshipDb>
)