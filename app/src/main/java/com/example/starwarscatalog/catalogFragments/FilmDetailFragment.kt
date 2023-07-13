package com.example.starwarscatalog.catalogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarscatalog.MyApp
import com.example.starwarscatalog.R
import com.example.starwarscatalog.adapters.AdapterMiniature
import com.example.starwarscatalog.data.FilmDb
import com.example.starwarscatalog.getDrawableImage
import com.example.starwarscatalog.viewmodel.ServerViewModel
import com.example.starwarscatalog.viewmodel.ViewModelFactory

class FilmDetailFragment : Fragment(R.layout.fragment_film_detail) {

    private val viewModel: ServerViewModel by activityViewModels { ViewModelFactory((activity?.application as MyApp).repository) }

    private var filmDetail: FilmDb? = null
    private var argsMain: FilmDb? = null
    private var argsUrlDetail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        argsMain = arguments?.getSerializable("film") as FilmDb?
        argsUrlDetail = arguments?.getString("filmDetail")
        argsUrlDetail?.let { viewModel.getDetailFilm(it) }
        filmDetail = argsMain
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmDetail?.let { getDetail(it) }
        viewModel.detailFilmVIewModel.observe(viewLifecycleOwner) { it ->
            view.findViewById<ImageView>(R.id.image_film_detail)
                .apply { this!!.setImageResource(requireContext().getDrawableImage(it.url)) }
            view.findViewById<TextView>(R.id.title_detail_film)
                .apply { this!!.text = getString(R.string.title, it.title) }
            view.findViewById<TextView>(R.id.episode_detail_film)
                .apply { this!!.text = getString(R.string.episode, it.episode_id.toString()) }
            view.findViewById<TextView>(R.id.synopsis_detail_film)
                .apply { this!!.text = getString(R.string.synopsis, it.opening_crawl) }
            view.findViewById<TextView>(R.id.director_detail_film)
                .apply { this!!.text = getString(R.string.director, it.director) }
            view.findViewById<TextView>(R.id.producer_detail_film)
                .apply { this!!.text = getString(R.string.producer, it.producer) }
            view.findViewById<TextView>(R.id.release_detail_film)
                .apply { this!!.text = getString(R.string.release, it.release_date) }
            view.findViewById<RecyclerView>(R.id.image_detail_character).apply {
                this!!.adapter = AdapterMiniature(it.characters) {
                    findNavController().navigate(
                        R.id.action_filmDetailFragment_to_personDetailFragment,
                        bundleOf("peopleDetail" to it)
                    )
                }
            }
            view.findViewById<RecyclerView>(R.id.image_detail_planet).apply {
                this!!.adapter = AdapterMiniature(it.planets) {
                    findNavController().navigate(
                        R.id.action_filmDetailFragment_to_planetDetailFragment,
                        bundleOf("planetDetail" to it)
                    )
                }
            }
            view.findViewById<RecyclerView>(R.id.image_detail_species).apply {
                this!!.adapter = AdapterMiniature(it.species) {
                    findNavController().navigate(
                        R.id.action_filmDetailFragment_to_specieDetailFragment,
                        bundleOf("specieDetail" to it)
                    )
                }
            }
            view.findViewById<RecyclerView>(R.id.image_detail_vehicles).apply {
                this!!.adapter = AdapterMiniature(it.vehicles) {
                    findNavController().navigate(
                        R.id.action_filmDetailFragment_to_vehiclesFragmentDetail,
                        bundleOf("vehicleDetail" to it)
                    )
                }
            }
            view.findViewById<RecyclerView>(R.id.image_detail_starship).apply {
                this!!.adapter = AdapterMiniature(it.starships) {
                    findNavController().navigate(
                        R.id.action_filmDetailFragment_to_starshipDetailFragment,
                        bundleOf("starshipDetail" to it)
                    )
                }
            }
        }
    }

    private fun getDetail(filmDetail: FilmDb) {
        view?.findViewById<ImageView>(R.id.image_film_detail)
            .apply { this!!.setImageResource(requireContext().getDrawableImage(filmDetail.url)) }
        view?.findViewById<TextView>(R.id.title_detail_film)
            .apply { this!!.text = getString(R.string.title, filmDetail.title) }
        view?.findViewById<TextView>(R.id.episode_detail_film)
            .apply { this!!.text = getString(R.string.episode, filmDetail.episode_id.toString()) }
        view?.findViewById<TextView>(R.id.synopsis_detail_film)
            .apply { this!!.text = getString(R.string.synopsis, filmDetail.opening_crawl) }
        view?.findViewById<TextView>(R.id.director_detail_film)
            .apply { this!!.text = getString(R.string.director, filmDetail.director) }
        view?.findViewById<TextView>(R.id.producer_detail_film)
            .apply { this!!.text = getString(R.string.producer, filmDetail.producer) }
        view?.findViewById<TextView>(R.id.release_detail_film)
            .apply { this!!.text = getString(R.string.release, filmDetail.release_date) }
        view?.findViewById<RecyclerView>(R.id.image_detail_character).apply {
            this!!.adapter = AdapterMiniature(filmDetail.characters) {
                findNavController().navigate(
                    R.id.action_filmDetailFragment_to_personDetailFragment,
                    bundleOf("peopleDetail" to it)
                )
            }
        }
        view?.findViewById<RecyclerView>(R.id.image_detail_planet).apply {
            this!!.adapter = AdapterMiniature(filmDetail.planets) {
                findNavController().navigate(
                    R.id.action_filmDetailFragment_to_planetDetailFragment,
                    bundleOf("planetDetail" to it)
                )
            }
        }
        view?.findViewById<RecyclerView>(R.id.image_detail_species).apply {
            this!!.adapter = AdapterMiniature(filmDetail.species) {
                findNavController().navigate(
                    R.id.action_filmDetailFragment_to_specieDetailFragment,
                    bundleOf("specieDetail" to it)
                )
            }
        }
        view?.findViewById<RecyclerView>(R.id.image_detail_vehicles).apply {
            this!!.adapter = AdapterMiniature(filmDetail.vehicles) {
                findNavController().navigate(
                    R.id.action_filmDetailFragment_to_vehiclesFragmentDetail,
                    bundleOf("vehicleDetail" to it)
                )
            }
        }
        view?.findViewById<RecyclerView>(R.id.image_detail_starship).apply {
            this!!.adapter = AdapterMiniature(filmDetail.starships) {
                findNavController().navigate(
                    R.id.action_filmDetailFragment_to_starshipDetailFragment,
                    bundleOf("starshipDetail" to it)
                )
            }
        }
    }
}