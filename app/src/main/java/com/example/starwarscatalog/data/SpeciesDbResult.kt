package com.example.starwarscatalog.data

data class SpeciesDbResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<SpeciesDb>
)