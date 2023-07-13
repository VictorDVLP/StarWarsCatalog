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
import com.example.starwarscatalog.data.PeopleDb
import com.example.starwarscatalog.databinding.FragmentPersonDetailBinding
import com.example.starwarscatalog.getDrawableImage
import com.example.starwarscatalog.viewmodel.ServerViewModel
import com.example.starwarscatalog.viewmodel.ViewModelFactory

class PersonDetailFragment : Fragment() {

    private var _binding: FragmentPersonDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ServerViewModel by activityViewModels { ViewModelFactory((activity?.application as MyApp).repository) }

    private var detailPeople: PeopleDb? = null
    private var argsMain: PeopleDb? = null
    private var argsUrlDetail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonDetailBinding.inflate(inflater, container, false)
        argsUrlDetail = arguments?.getString("peopleDetail")
        argsUrlDetail?.let { viewModel.getDetailPeople(it) }
        argsMain = arguments?.getSerializable("people") as PeopleDb?
        detailPeople = argsMain
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailPeople?.let { getDetail(it) }
        viewModel.detailPeopleVIewModel.observe(viewLifecycleOwner) { it ->
            binding.imagePersonDetail.setImageResource(requireContext().getDrawableImage(it.url))
            binding.nameDetailPerson.text = getString(R.string.name, it.name)
            binding.birthDetailPerson.text = getString(R.string.birth_year, it.birth_year)
            binding.massDetailPerson.text = getString(R.string.mass, it.mass)
            binding.heightDetailPerson.text = getString(R.string.height, it.height)
            binding.genderDetailPerson.text = getString(R.string.gender, it.gender)
            binding.hairDetailPerson.text = getString(R.string.hair, it.hair_color)
            binding.eyeDetailPerson.text = getString(R.string.eye, it.eye_color)
            binding.skinDetailPerson.text = getString(R.string.skin, it.skin_color)
            binding.imageDetailHomeworld.adapter =
                AdapterMiniature(it.homeworld.lines()) {
                    findNavController().navigate(
                        R.id.action_personDetailFragment_to_planetDetailFragment,
                        bundleOf("planetDetail" to it)
                    )
                }
            binding.imageDetailSpecies.adapter =
                AdapterMiniature(it.species) {
                    findNavController().navigate(
                        R.id.action_personDetailFragment_to_specieDetailFragment,
                        bundleOf("specieDetail" to it)
                    )
                }
            binding.imageDetailVehicles.adapter =
                AdapterMiniature(it.vehicles) {
                    findNavController().navigate(
                        R.id.action_personDetailFragment_to_vehiclesFragmentDetail,
                        bundleOf("vehicleDetail" to it)
                    )
                }
            binding.imageDetailStarship.adapter =
                AdapterMiniature(it.starships) {
                    findNavController().navigate(
                        R.id.action_personDetailFragment_to_starshipDetailFragment,
                        bundleOf("starshipDetail" to it)
                    )
                }
            binding.imageDetailFilms.adapter =
                AdapterMiniature(it.films) {
                    findNavController().navigate(
                        R.id.action_personDetailFragment_to_filmDetailFragment,
                        bundleOf("filmDetail" to it)
                    )
                }
        }
    }

    private fun getDetail(detailPeople: PeopleDb) {
        binding.imagePersonDetail.setImageResource(requireContext().getDrawableImage(detailPeople.url))
        binding.nameDetailPerson.text = getString(R.string.name, detailPeople.name)
        binding.birthDetailPerson.text = getString(R.string.birth_year, detailPeople.birth_year)
        binding.massDetailPerson.text = getString(R.string.mass, detailPeople.mass)
        binding.heightDetailPerson.text = getString(R.string.height, detailPeople.height)
        binding.genderDetailPerson.text = getString(R.string.gender, detailPeople.gender)
        binding.hairDetailPerson.text = getString(R.string.hair, detailPeople.hair_color)
        binding.eyeDetailPerson.text = getString(R.string.eye, detailPeople.eye_color)
        binding.skinDetailPerson.text = getString(R.string.skin, detailPeople.skin_color)
        binding.imageDetailHomeworld.adapter =
            AdapterMiniature(detailPeople.homeworld.lines()) {
                findNavController().navigate(
                    R.id.action_personDetailFragment_to_planetDetailFragment,
                    bundleOf("planetDetail" to it)
                )
            }
        binding.imageDetailSpecies.adapter =
            AdapterMiniature(detailPeople.species) {
                findNavController().navigate(
                    R.id.action_personDetailFragment_to_specieDetailFragment,
                    bundleOf("specieDetail" to it)
                )
            }
        binding.imageDetailVehicles.adapter =
            AdapterMiniature(detailPeople.vehicles) {
                findNavController().navigate(
                    R.id.action_personDetailFragment_to_vehiclesFragmentDetail,
                    bundleOf("vehiclesDetail" to it)
                )
            }
        binding.imageDetailStarship.adapter =
            AdapterMiniature(detailPeople.starships) {
                findNavController().navigate(
                    R.id.action_personDetailFragment_to_starshipDetailFragment,
                    bundleOf("starshipDetail" to it)
                )
            }
        binding.imageDetailFilms.adapter =
            AdapterMiniature(detailPeople.films) {
                findNavController().navigate(
                    R.id.action_personDetailFragment_to_filmDetailFragment,
                    bundleOf("filmDetail" to it)
                )
            }
    }
}
