package com.example.starwarscatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.starwarscatalog.databinding.FragmentMainBinding

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val buttonPerson = binding.personButton
        val buttonPlanet = binding.planetButton
        val buttonVehicles = binding.vehiclesButton
        val buttonFilms = binding.filmsButton
        val buttonSpecies = binding.speciesButton
        val buttonStartShips = binding.startshipsButton

        buttonPerson.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_personFragment) }
        buttonPlanet.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_planetFragment) }
        buttonVehicles.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_vehiclesFragment) }
        buttonFilms.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_filmFragment) }
        buttonSpecies.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_speciesFragment) }
        buttonStartShips.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_starshipFragment) }
    }
}