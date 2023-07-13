package com.example.starwarscatalog.data

import java.io.Serializable

data class PeopleDbResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PeopleDb>
) : Serializable