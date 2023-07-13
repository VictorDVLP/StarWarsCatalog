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
import com.example.starwarscatalog.data.PlanetDb
import com.example.starwarscatalog.databinding.FragmentPlanetDetailBinding
import com.example.starwarscatalog.getDrawableImage
import com.example.starwarscatalog.viewmodel.ServerViewModel
import com.example.starwarscatalog.viewmodel.ViewModelFactory

class PlanetDetailFragment : Fragment() {

    private var _binding: FragmentPlanetDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ServerViewModel by activityViewModels { ViewModelFactory((activity?.application as MyApp).repository) }

    private var detailPlanet: PlanetDb? = null
    private var argsMain: PlanetDb? = null
    private var argsUrlDetail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        argsUrlDetail = arguments?.getString("planetDetail")
        argsUrlDetail?.let { viewModel.getDetailPlanet(it) }
        argsMain = arguments?.getSerializable("planet") as PlanetDb?
        detailPlanet = argsMain
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         detailPlanet?.let { getDetail(it) }
        viewModel.detailPlanetViewModel.observe(viewLifecycleOwner) { it ->
            binding.imagePlanetDetail.setImageResource(requireContext().getDrawableImage(it.url))
            binding.nameDetailPlanet.text = getString(R.string.name, it.name)
            binding.rotationDetailPlanet.text = getString(R.string.rotation, it.rotation_period)
            binding.orbitalDetailPlanet.text = getString(R.string.orbital, it.rotation_period)
            binding.diameterDetailPlanet.text = getString(R.string.diameter, it.diameter)
            binding.gravityDetailPlanet.text = getString(R.string.gravity, it.gravity)
            binding.terrainDetailPlanet.text = getString(R.string.terrain, it.terrain)
            binding.waterDetailPlanet.text = getString(R.string.surface_water, it.surface_water)
            binding.populationDetailPlanet.text = getString(R.string.population, it.population)
            binding.imageDetailResident.adapter = AdapterMiniature(it.residents) {
                findNavController().navigate(
                    R.id.action_planetDetailFragment_to_personDetailFragment,
                    bundleOf("peopleDetail" to it)
                )
            }
            binding.imageDetailFilms.adapter = AdapterMiniature(it.films) {
                findNavController().navigate(
                    R.id.action_planetDetailFragment_to_filmDetailFragment,
                    bundleOf("filmDetail" to it)
                )
            }
        }
    }

    private fun getDetail(detailPlanet: PlanetDb) {
        binding.imagePlanetDetail.setImageResource(requireContext().getDrawableImage(detailPlanet.url))
        binding.nameDetailPlanet.text = getString(R.string.name, detailPlanet.name)
        binding.rotationDetailPlanet.text = getString(R.string.rotation, detailPlanet.rotation_period)
        binding.orbitalDetailPlanet.text = getString(R.string.orbital, detailPlanet.rotation_period)
        binding.diameterDetailPlanet.text = getString(R.string.diameter, detailPlanet.diameter)
        binding.gravityDetailPlanet.text = getString(R.string.gravity, detailPlanet.gravity)
        binding.terrainDetailPlanet.text = getString(R.string.terrain, detailPlanet.terrain)
        binding.waterDetailPlanet.text = getString(R.string.surface_water, detailPlanet.surface_water)
        binding.populationDetailPlanet.text = getString(R.string.population, detailPlanet.population)
        binding.imageDetailResident.adapter = AdapterMiniature(detailPlanet.residents) {
            findNavController().navigate(
                R.id.action_planetDetailFragment_to_personDetailFragment,
                bundleOf("peopleDetail" to it)
            )
        }
        binding.imageDetailFilms.adapter = AdapterMiniature(detailPlanet.films) {
            findNavController().navigate(
                R.id.action_planetDetailFragment_to_filmDetailFragment,
                bundleOf("filmDetail" to it)
            )
        }
    }
}