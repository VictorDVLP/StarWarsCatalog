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
import com.example.starwarscatalog.data.VehiclesDb
import com.example.starwarscatalog.getDrawableImage
import com.example.starwarscatalog.viewmodel.ServerViewModel
import com.example.starwarscatalog.viewmodel.ViewModelFactory

class VehicleDetailFragment : Fragment(R.layout.fragment_vehicles_detail) {

    private var vehicleDetail: VehiclesDb? = null
    private var argsMain: VehiclesDb? = null
    private var argsUrlDetail: String? = null

    private val viewModel: ServerViewModel by activityViewModels { ViewModelFactory((activity?.application as MyApp).repository) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        argsUrlDetail = arguments?.getString("vehicleDetail")
        argsUrlDetail?.let { viewModel.getDetailVehicle(it) }
        argsMain = arguments?.getSerializable("vehicles") as VehiclesDb?
        vehicleDetail = argsMain
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vehicleDetail?.let { getDetail(it) }
        viewModel.detailVehicleVIewModel.observe(viewLifecycleOwner) { it ->
            view.findViewById<ImageView>(R.id.image_vehicles_detail)
                .apply { setImageResource(requireContext().getDrawableImage(it.url)) }
            view.findViewById<TextView>(R.id.name_detail_vehicle)
                .apply { text = getString(R.string.name, it.name) }
            view.findViewById<TextView>(R.id.model_detail_vehicles)
                .apply { text = getString(R.string.model, it.model) }
            view.findViewById<TextView>(R.id.manufacter_detail_vehicle)
                .apply { text = getString(R.string.manufactur, it.manufacturer) }
            view.findViewById<TextView>(R.id.cost_detail_vehicles)
                .apply { text = getString(R.string.cost_of_credits, it.cost_in_credits) }
            view.findViewById<TextView>(R.id.length_detail_vehicles)
                .apply { text = getString(R.string.length, it.length) }
            view.findViewById<TextView>(R.id.maxspeed_detail_vehicles)
                .apply { text = getString(R.string.maxspeed, it.max_atmosphering_speed) }
            view.findViewById<TextView>(R.id.crew_detail_vehicles)
                .apply { text = getString(R.string.crew, it.crew) }
            view.findViewById<TextView>(R.id.passenger_detail_vehicles)
                .apply { text = getString(R.string.passengers, it.passengers) }
            view.findViewById<TextView>(R.id.cargo_detail_vehicles)
                .apply { text = getString(R.string.cargo, it.cargo_capacity) }
            view.findViewById<TextView>(R.id.name_detail_vehicle)
                .apply { text = getString(R.string.name, it.name) }
            view.findViewById<TextView>(R.id.consumables_detail_vehicles)
                .apply { text = getString(R.string.consumables, it.consumables) }
            view.findViewById<TextView>(R.id.vehicleclass_detail_vehicles)
                .apply { text = getString(R.string.vehiclesclass, it.vehicle_class) }
            view.findViewById<RecyclerView>(R.id.image_detail_pilots).apply {
                adapter = AdapterMiniature(it.pilots) {
                    findNavController().navigate(
                        R.id.action_vehiclesFragmentDetail_to_personDetailFragment,
                        bundleOf("peopleDetail" to it)
                    )
                }
            }
            view.findViewById<RecyclerView>(R.id.image_detail_films).apply {
                adapter = AdapterMiniature(it.films) {
                    findNavController().navigate(
                        R.id.action_vehiclesFragmentDetail_to_filmDetailFragment,
                        bundleOf("filmDetail" to it)
                    )
                }
            }
        }
    }

    private fun getDetail(vehicleDetail: VehiclesDb) {
        view?.findViewById<ImageView>(R.id.image_vehicles_detail)
            .apply { this!!.setImageResource(requireContext().getDrawableImage(vehicleDetail.url)) }
        view?.findViewById<TextView>(R.id.name_detail_vehicle)
            .apply { this!!.text = getString(R.string.name, vehicleDetail.name) }
        view?.findViewById<TextView>(R.id.model_detail_vehicles)
            .apply { this!!.text = getString(R.string.model, vehicleDetail.model) }
        view?.findViewById<TextView>(R.id.manufacter_detail_vehicle)
            .apply { this!!.text = getString(R.string.manufactur, vehicleDetail.manufacturer) }
        view?.findViewById<TextView>(R.id.cost_detail_vehicles).apply {
            this!!.text = getString(R.string.cost_of_credits, vehicleDetail.cost_in_credits)
        }
        view?.findViewById<TextView>(R.id.length_detail_vehicles)
            .apply { this!!.text = getString(R.string.length, vehicleDetail.length) }
        view?.findViewById<TextView>(R.id.maxspeed_detail_vehicles).apply {
            this!!.text = getString(R.string.maxspeed, vehicleDetail.max_atmosphering_speed)
        }
        view?.findViewById<TextView>(R.id.crew_detail_vehicles)
            .apply { this!!.text = getString(R.string.crew, vehicleDetail.crew) }
        view?.findViewById<TextView>(R.id.passenger_detail_vehicles)
            .apply { this!!.text = getString(R.string.passengers, vehicleDetail.passengers) }
        view?.findViewById<TextView>(R.id.cargo_detail_vehicles)
            .apply { this!!.text = getString(R.string.cargo, vehicleDetail.cargo_capacity) }
        view?.findViewById<TextView>(R.id.name_detail_vehicle)
            .apply { this!!.text = getString(R.string.name, vehicleDetail.name) }
        view?.findViewById<TextView>(R.id.consumables_detail_vehicles)
            .apply { this!!.text = getString(R.string.consumables, vehicleDetail.consumables) }
        view?.findViewById<TextView>(R.id.vehicleclass_detail_vehicles)
            .apply { this!!.text = getString(R.string.vehiclesclass, vehicleDetail.vehicle_class) }
        view?.findViewById<RecyclerView>(R.id.image_detail_pilots).apply {
            this!!.adapter = AdapterMiniature(vehicleDetail.pilots) {
                findNavController().navigate(
                    R.id.action_vehiclesFragmentDetail_to_personDetailFragment,
                    bundleOf("peopleDetail" to it)
                )
            }
        }
        view?.findViewById<RecyclerView>(R.id.image_detail_films).apply {
            this!!.adapter = AdapterMiniature(vehicleDetail.films) {
                findNavController().navigate(
                    R.id.action_vehiclesFragmentDetail_to_filmDetailFragment,
                    bundleOf("filmDetail" to it)
                )
            }
        }
    }
}