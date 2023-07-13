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
import com.example.starwarscatalog.data.SpeciesDb
import com.example.starwarscatalog.databinding.FragmentSpeciesDetailBinding
import com.example.starwarscatalog.getDrawableImage
import com.example.starwarscatalog.viewmodel.ServerViewModel
import com.example.starwarscatalog.viewmodel.ViewModelFactory

class SpecieDetailFragment : Fragment() {

    private var _binding: FragmentSpeciesDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ServerViewModel by activityViewModels { ViewModelFactory((activity?.application as MyApp).repository) }

    private var detailSpecie: SpeciesDb? = null
    private var argsMain: SpeciesDb? = null
    private var argsUrlDetail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        argsUrlDetail = arguments?.getString("specieDetail")
        argsUrlDetail?.let { viewModel.getDetailSpecie(it) }
        argsMain = arguments?.getSerializable("specie") as SpeciesDb?
        detailSpecie = argsMain
        _binding = FragmentSpeciesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailSpecie?.let { getDetail(it) }
        viewModel.detailSpecieVIewModel.observe(viewLifecycleOwner) { it ->
            binding.imageSpecieDetail.setImageResource(
                requireContext().getDrawableImage(
                    it.url
                )
            )
            binding.nameDetailSpecie.text = getString(R.string.name, it.name)
            binding.classificationDetailSpecie.text =
                getString(R.string.classification, it.classification)
            binding.designationDetailSpecie.text =
                getString(R.string.designation, it.designation)
            binding.averageHeightDetailSpecie.text =
                getString(R.string.average, it.average_height)
            binding.skinDetailSpecie.text = getString(R.string.skin, it.skin_colors)
            binding.eyeDetailSpecie.text = getString(R.string.eye, it.eye_colors)
            binding.hairDetailSpecie.text = getString(R.string.hair, it.hair_colors)
            binding.lifespanDetailSpecie.text =
                getString(R.string.lifespan, it.average_lifespan)
            binding.languageDetailSpecie.text = getString(R.string.language, it.language)
            binding.imageDetailHomeworld.adapter = AdapterMiniature(
                it.homeworld.lines()
            ) {
                findNavController().navigate(
                    R.id.action_specieDetailFragment_to_planetDetailFragment,
                    bundleOf("planetDetail" to it)
                )
            }
            binding.imageDetailPeoplee.adapter = AdapterMiniature(
                it.people
            ) {
                findNavController().navigate(
                    R.id.action_specieDetailFragment_to_personDetailFragment,
                    bundleOf("peopleDetail" to it)
                )
            }
            binding.imageDetailFilms.adapter = AdapterMiniature(
                it.films
            ) {
                findNavController().navigate(
                    R.id.action_specieDetailFragment_to_filmDetailFragment,
                    bundleOf("filmDetail" to it)
                )
            }
        }
    }


    private fun getDetail(detailSpecie: SpeciesDb) {
        binding.imageSpecieDetail.setImageResource(requireContext().getDrawableImage(detailSpecie.url))
        binding.nameDetailSpecie.text = getString(R.string.name, detailSpecie.name)
        binding.classificationDetailSpecie.text =
            getString(R.string.classification, detailSpecie.classification)
        binding.designationDetailSpecie.text =
            getString(R.string.designation, detailSpecie.designation)
        binding.averageHeightDetailSpecie.text =
            getString(R.string.average, detailSpecie.average_height)
        binding.skinDetailSpecie.text = getString(R.string.skin, detailSpecie.skin_colors)
        binding.eyeDetailSpecie.text = getString(R.string.eye, detailSpecie.eye_colors)
        binding.hairDetailSpecie.text = getString(R.string.hair, detailSpecie.hair_colors)
        binding.lifespanDetailSpecie.text =
            getString(R.string.lifespan, detailSpecie.average_lifespan)
        binding.languageDetailSpecie.text = getString(R.string.language, detailSpecie.language)
        binding.imageDetailHomeworld.adapter = AdapterMiniature(
            detailSpecie.homeworld.lines()
        ) {
            findNavController().navigate(
                R.id.action_specieDetailFragment_to_planetDetailFragment,
                bundleOf("planetDetail" to it)
            )
        }
        binding.imageDetailPeoplee.adapter = AdapterMiniature(
            detailSpecie.people
        ) {
            findNavController().navigate(
                R.id.action_specieDetailFragment_to_personDetailFragment,
                bundleOf("peopleDetail" to it)
            )
        }
        binding.imageDetailFilms.adapter = AdapterMiniature(
            detailSpecie.films
        ) {
            findNavController().navigate(
                R.id.action_specieDetailFragment_to_filmDetailFragment,
                bundleOf("filmDetail" to it)
            )
        }
    }
}