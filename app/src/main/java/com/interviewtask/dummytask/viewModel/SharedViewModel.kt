package com.example.maptask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.interviewtask.dummytask.model.MapListModel
import com.interviewtask.dummytask.utils.getMarkerListFromLocal
import com.interviewtask.dummytask.utils.saveMarkerListInLocal

class SharedViewModel : ViewModel() {

    private val _markerList = MutableLiveData<ArrayList<MapListModel>>()
    val markerList: LiveData<ArrayList<MapListModel>> get() = _markerList

    private var list: ArrayList<MapListModel> = ArrayList()
    init {
        _markerList.value = getMarkerListFromLocal()
        list= getMarkerListFromLocal()
    }

    fun saveMarkerList(model: MapListModel) {
        list.add(model)
        _markerList.postValue(list)
        saveMarkerListInLocal(list) // saving all markers
    }

    fun deleteMapMarker(model:MapListModel){
        list.remove(model)
        _markerList.postValue(list)
        saveMarkerListInLocal(list)

    }
}