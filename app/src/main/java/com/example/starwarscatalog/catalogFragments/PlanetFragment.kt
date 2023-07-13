package com.example.starwarscatalog.catalogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.starwarscatalog.MyApp
import com.example.starwarscatalog.R
import com.example.starwarscatalog.adapters.PlanetAdapter
import com.example.starwarscatalog.databinding.FragmentPlanetBinding
import com.example.starwarscatalog.viewmodel.ServerViewModel
import com.example.starwarscatalog.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlanetFragment : Fragment() {

    private var _binding: FragmentPlanetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ServerViewModel by activityViewModels { ViewModelFactory((activity?.application as MyApp).repository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PlanetAdapter {
            findNavController().navigate(
                R.id.action_planetFragment_to_planetDetailFragment, bundleOf("planet" to it)
            )
        }
        lifecycleScope.launch {
            viewModel.responsePlanet.collectLatest { pagingData ->
                adapter.submitData(
                    pagingData
                )
            }
        }
        binding.recyclerPlanet.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerPlanet.adapter = adapter
    }
}