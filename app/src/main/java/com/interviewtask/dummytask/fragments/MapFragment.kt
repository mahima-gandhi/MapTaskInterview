package com.interviewtask.dummytask.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.maptask.viewModel.SharedViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.interviewtask.dummytask.R
import com.interviewtask.dummytask.databinding.FragmentMapBinding
import com.interviewtask.dummytask.model.MapListModel


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding
    private lateinit var mMap: GoogleMap
    private var mMarkrList: ArrayList<MapListModel> = ArrayList()
    private val viewModel: SharedViewModel by activityViewModels()
    private fun attachObserver() {
        //getting list from viewModel
        viewModel.markerList.observe(
            viewLifecycleOwner
        ) {
            if (this::mMap.isInitialized){
                mMap.clear()
                mMarkrList.clear()
                mMarkrList.addAll(it)
                setUps()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMapBinding.inflate(layoutInflater)
        attachObserver()
        setUps()
        return binding.root

    }

    private fun setUps() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val icon: BitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.ic_mrk)

        mMap.setOnMapClickListener {
            mMap.addMarker(
                MarkerOptions().position(it).icon(icon)
            )
            mMarkrList.add(MapListModel(it.latitude, it.longitude))
            viewModel.saveMarkerList(MapListModel(it.latitude, it.longitude))

            Log.e("TAG", "onMapReady: $mMarkrList")


        }
        // showing markers on screen
        if (mMarkrList.isNotEmpty()) {
            mMarkrList.forEach {
                mMap.addMarker(
                    MarkerOptions().position(LatLng(it.lat, it.long))
                        .icon(icon)
                )
            }
        }
    }
}