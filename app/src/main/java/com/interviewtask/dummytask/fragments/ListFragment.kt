package com.interviewtask.dummytask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maptask.adapters.ListAdapter
import com.example.maptask.viewModel.SharedViewModel
import com.interviewtask.dummytask.R
import com.interviewtask.dummytask.databinding.FragmentListBinding
import com.interviewtask.dummytask.model.MapListModel


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var mList: ArrayList<MapListModel> = ArrayList()
    private lateinit var mAdapter: ListAdapter
    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        setUps()
        attachObserver()

        return binding.root
    }

    private fun attachObserver() {
        viewModel.markerList.observe(
            viewLifecycleOwner
        ) {
            mList.clear()
            mList.addAll(it)
            mAdapter.notifyDataSetChanged()

        }
    }

    private fun setUps() { //setting adapter
        binding.listRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter = ListAdapter(mList, onClick = { position, _ ->
                viewModel.deleteMapMarker(mList[position])
                mList.removeAt(position)
                mAdapter.notifyDataSetChanged()
            })
            adapter = mAdapter
        }
    }

}