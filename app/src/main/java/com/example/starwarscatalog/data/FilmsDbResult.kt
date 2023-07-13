package com.example.starwarscatalog.data

import java.io.Serializable

data class FilmsDbResult(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<FilmDb>
) : Serializable