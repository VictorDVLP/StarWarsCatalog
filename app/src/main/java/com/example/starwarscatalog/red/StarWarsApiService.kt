package com.example.starwarscatalog.red

import com.example.starwarscatalog.data.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

private const val BASE_URL = "https://swapi.py4e.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface StarWarsApiServer {
    @GET("people/")
    suspend fun getItemPerson(@Query("page") page: Int): PeopleDbResult

    @GET("planets/")
    suspend fun getItemPlanet(@Query("page") page: Int): PlanetDbResult

    @GET("vehicles/")
    suspend fun getItemVehicles(@Query("page") page: Int): VehiclesDbResult

    @GET("films/")
    suspend fun getItemFilms(@Query("page") page: Int): FilmsDbResult

    @GET("species/")
    suspend fun getItemSpecies(@Query("page") page: Int): SpeciesDbResult

    @GET("starships/")
    suspend fun getItemStarship(@Query("page") page: Int): StarshipDbResult

    @GET
    suspend fun getItemPlanetDetail(@Url url: String): PlanetDb

    @GET
    suspend fun getItemPeopleDetail(@Url url: String): PeopleDb

    @GET
    suspend fun getItemFilmDetail(@Url url: String): FilmDb

    @GET
    suspend fun getItemSpecieDetail(@Url url: String): SpeciesDb

    @GET
    suspend fun getItemVehicleDetail(@Url url: String): VehiclesDb

    @GET
    suspend fun getItemStarshipDetail(@Url url: String): StarshipDb
}

object StarWarsApi {
    val retrofitService: StarWarsApiServer by lazy {
        retrofit.create(StarWarsApiServer::class.java)
    }
}
