package com.example.starwarscatalog.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarscatalog.data.*
import com.example.starwarscatalog.pagination.*
import com.example.starwarscatalog.red.StarWarsApi
import kotlinx.coroutines.flow.Flow

const val NETWORK_PAGE_SIZE = 10
const val START_INDEX = 1

class StarWarsRepository(private val service: StarWarsApi) {

    fun getResultListPeople(): Flow<PagingData<PeopleDb>> {
        return Pager(config = PagingConfig
            (
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { PeoplePagination(service) }).flow
    }

    fun getResultListPlanet(): Flow<PagingData<PlanetDb>> {
        return Pager(config = PagingConfig
            (
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { PlanetPagination(service) }).flow
    }

    fun getResultListVehicles(): Flow<PagingData<VehiclesDb>> {
        return Pager(config = PagingConfig
            (
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { VehiclePagination(service) }).flow
    }

    fun getResultListFilms(): Flow<PagingData<FilmDb>> {
        return Pager(config = PagingConfig
            (
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { FilmPagination(service) }).flow
    }

    fun getResultListSpecies(): Flow<PagingData<SpeciesDb>> {
        return Pager(config = PagingConfig
            (
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { SpeciePagination(service) }).flow
    }

    fun getResultListStarship(): Flow<PagingData<StarshipDb>> {
        return Pager(config = PagingConfig
            (
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
            pagingSourceFactory = { StarshipPagination(service) }).flow
    }
}