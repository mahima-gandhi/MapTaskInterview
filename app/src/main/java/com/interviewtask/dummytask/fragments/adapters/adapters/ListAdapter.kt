package com.example.maptask.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.interviewtask.dummytask.databinding.ItemListBinding
import com.interviewtask.dummytask.model.MapListModel

class ListAdapter(private val list: ArrayList<MapListModel>, val onClick: (Int, MapListModel) -> Unit) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.onBind(list[position])

        holder.alarmItemBinding.ivDelete.setOnClickListener {
            onClick(position,list[position])
        }

    }

    override fun getItemCount() = list.size

    class MyViewHolder(var alarmItemBinding: ItemListBinding) :
        RecyclerView.ViewHolder(alarmItemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(s: MapListModel) {

            alarmItemBinding.tvLat.text = "LAT: ${s.lat}".substring(0,12)
            alarmItemBinding.tvLong.text = "LNG: ${s.long}".substring(0,12)

        }


    }
}