package com.example.starwarscatalog.catalogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.starwarscatalog.MyApp
import com.example.starwarscatalog.R
import com.example.starwarscatalog.adapters.AdapterMiniature
import com.example.starwarscatalog.data.StarshipDb
import com.example.starwarscatalog.databinding.FragmentStarshipDetailBinding
import com.example.starwarscatalog.getDrawableImage
import com.example.starwarscatalog.viewmodel.ServerViewModel
import com.example.starwarscatalog.viewmodel.ViewModelFactory

class StarshipDetailFragment: Fragment() {

    private var _biinding: FragmentStarshipDetailBinding? = null
    private val binding get() = _biinding!!

    private val viewModel: ServerViewModel by activityViewModels { ViewModelFactory((activity?.application as MyApp).repository) }

    private var detailStarship: StarshipDb? = null
    private var argsMain: StarshipDb? = null
    private var argsUrlDetail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        argsUrlDetail = arguments?.getString("starshipDetail")
        argsUrlDetail?.let { viewModel.getDetailStarship(it) }
        argsMain = arguments?.getSerializable("starship") as StarshipDb?
        detailStarship = argsMain
        _biinding = FragmentStarshipDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailStarship?.let { getDetail(it) }
        viewModel.detailStarshipVIewModel.observe(viewLifecycleOwner) { it ->
            binding.imageStarshipDetail.setImageResource(requireContext().getDrawableImage(it.url))
            binding.nameDetailStarship.text = getString(R.string.name, it.name)
            binding.modelDetailStarship.text = getString(R.string.model, it.model)
            binding.manufacturerDetailStarship.text = getString(R.string.manufactur, it.manufacturer)
            binding.costDetailStarship.text = getString(R.string.cost_of_credits, it.cost_in_credits)
            binding.lengthDetailStarship.text = getString(R.string.length, it.length)
            binding.maxspeedDetailStarship.text = getString(R.string.maxspeed, it.max_atmosphering_speed)
            binding.crewDetailStarship.text = getString(R.string.crew, it.crew)
            binding.passengerDetailStarship.text = getString(R.string.passengers, it.passengers)
            binding.cargoDetailStarship.text = getString(R.string.cargo, it.cargo_capacity)
            binding.consumablesDetailStarship.text = getString(R.string.consumables, it.consumables)
            binding.hyperdriveDetailStarship.text = getString(R.string.hyperdrive, it.hyperdrive_rating)
            binding.mgltDetailStarship.text = getString(R.string.mglt, it.MGLT)
            binding.classDetailStarship.text = getString(R.string.starshipclass, it.starship_class)
            binding.imageDetailPilots.adapter = AdapterMiniature(it.pilots) { findNavController().navigate(R.id.action_starshipDetailFragment_to_personDetailFragment, bundleOf("peopleDetail" to it))}
            binding.imageDetailFilms.adapter = AdapterMiniature(it.films) { findNavController().navigate(R.id.action_starshipDetailFragment_to_filmDetailFragment, bundleOf("filmDetail" to it))}
        }
    }

    private fun getDetail( detailStarship: StarshipDb ) {
        binding.imageStarshipDetail.setImageResource(requireContext().getDrawableImage(detailStarship.url))
        binding.nameDetailStarship.text = getString(R.string.name, detailStarship.name)
        binding.modelDetailStarship.text = getString(R.string.model, detailStarship.model)
        binding.manufacturerDetailStarship.text = getString(R.string.manufactur, detailStarship.manufacturer)
        binding.costDetailStarship.text = getString(R.string.cost_of_credits, detailStarship.cost_in_credits)
        binding.lengthDetailStarship.text = getString(R.string.length, detailStarship.length)
        binding.maxspeedDetailStarship.text = getString(R.string.maxspeed, detailStarship.max_atmosphering_speed)
        binding.crewDetailStarship.text = getString(R.string.crew, detailStarship.crew)
        binding.passengerDetailStarship.text = getString(R.string.passengers, detailStarship.passengers)
        binding.cargoDetailStarship.text = getString(R.string.cargo, detailStarship.cargo_capacity)
        binding.consumablesDetailStarship.text = getString(R.string.consumables, detailStarship.consumables)
        binding.hyperdriveDetailStarship.text = getString(R.string.hyperdrive, detailStarship.hyperdrive_rating)
        binding.mgltDetailStarship.text = getString(R.string.mglt, detailStarship.MGLT)
        binding.classDetailStarship.text = getString(R.string.starshipclass, detailStarship.starship_class)
        binding.imageDetailPilots.adapter = AdapterMiniature(detailStarship.pilots) { findNavController().navigate(R.id.action_starshipDetailFragment_to_personDetailFragment, bundleOf("peopleDetail" to it))}
        binding.imageDetailFilms.adapter = AdapterMiniature(detailStarship.films) { findNavController().navigate(R.id.action_starshipDetailFragment_to_filmDetailFragment, bundleOf("filmDetail" to it))}
    }
}