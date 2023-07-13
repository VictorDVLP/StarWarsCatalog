package com.example.starwarscatalog.data

import com.example.starwarscatalog.R

enum class Categories( val valueName: String, val icon: Int, val url: String ) {
    PEOPLE("people", R.drawable.people, "https://swapi.py4e.com/api/people/"),
    PLANETS("planets",R.drawable.planet,"https://swapi.py4e.com/api/planets/"),
    VEHICLES("vehicles",R.drawable.vehicle,"https://swapi.py4e.com/api/vehicles/"),
    FILMS("films",R.drawable.lightsaber,"https://swapi.py4e.com/api/films/"),
    SPECIES("species",R.drawable.species,"https://swapi.py4e.com/api/species/"),
    STARTSHIPS("starships",R.drawable.starships,"https://swapi.py4e.com/api/starships/")
}