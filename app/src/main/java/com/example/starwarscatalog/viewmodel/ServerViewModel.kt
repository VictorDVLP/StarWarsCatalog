package com.example.starwarscatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.starwarscatalog.data.*
import com.example.starwarscatalog.red.StarWarsApi
import com.example.starwarscatalog.repository.StarWarsRepository
import kotlinx.coroutines.launch

class ServerViewModel(
    repository: StarWarsRepository
) : ViewModel() {

    val responsePeople = repository.getResultListPeople().cachedIn(viewModelScope)
    val responsePlanet = repository.getResultListPlanet().cachedIn(viewModelScope)
    val responseVehicles = repository.getResultListVehicles().cachedIn(viewModelScope)
    val responseFilms = repository.getResultListFilms().cachedIn(viewModelScope)
    val responseSpecies = repository.getResultListSpecies().cachedIn(viewModelScope)
    val responseStarships = repository.getResultListStarship().cachedIn(viewModelScope)

    private val _detailPlanet = MutableLiveData<PlanetDb>()
    val detailPlanetViewModel: LiveData<PlanetDb> = _detailPlanet

    private val _detailPeople = MutableLiveData<PeopleDb>()
    val detailPeopleVIewModel: LiveData<PeopleDb> = _detailPeople

    private val _detailFilm = MutableLiveData<FilmDb>()
    val detailFilmVIewModel: LiveData<FilmDb> = _detailFilm

    private val _detailSpecie = MutableLiveData<SpeciesDb>()
    val detailSpecieVIewModel: LiveData<SpeciesDb> = _detailSpecie

    private val _detailVehicle = MutableLiveData<VehiclesDb>()
    val detailVehicleVIewModel: LiveData<VehiclesDb> = _detailVehicle

    private val _detailStarship = MutableLiveData<StarshipDb>()
    val detailStarshipVIewModel: LiveData<StarshipDb> = _detailStarship

    fun getDetailPlanet(url: String) {
        viewModelScope.launch {
            val planet = StarWarsApi.retrofitService.getItemPlanetDetail(url)
            _detailPlanet.value = planet
        }
    }

    fun getDetailPeople(url: String) {
        viewModelScope.launch {
            val people = StarWarsApi.retrofitService.getItemPeopleDetail(url)
            _detailPeople.value = people
        }
    }

    fun getDetailFilm(url: String) {
        viewModelScope.launch {
            val film = StarWarsApi.retrofitService.getItemFilmDetail(url)
            _detailFilm.value = film
        }
    }

    fun getDetailSpecie(url: String) {
        viewModelScope.launch {
            val specie = StarWarsApi.retrofitService.getItemSpecieDetail(url)
            _detailSpecie.value = specie
        }
    }

    fun getDetailVehicle(url: String) {
        viewModelScope.launch {
            val vehicle = StarWarsApi.retrofitService.getItemVehicleDetail(url)
            _detailVehicle.value = vehicle
        }
    }

    fun getDetailStarship(url: String) {
        viewModelScope.launch {
            val starship = StarWarsApi.retrofitService.getItemStarshipDetail(url)
            _detailStarship.value = starship
        }
    }
}