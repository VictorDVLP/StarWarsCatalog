package com.example.starwarscatalog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarscatalog.repository.StarWarsRepository

class ViewModelFactory(
    private val repository: StarWarsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ServerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}