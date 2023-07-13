package com.example.starwarscatalog

import android.app.Application
import com.example.starwarscatalog.red.StarWarsApi
import com.example.starwarscatalog.repository.StarWarsRepository

class MyApp: Application() {
    private val database by lazy { StarWarsApi }
    val repository: StarWarsRepository by lazy { StarWarsRepository(database) }
}