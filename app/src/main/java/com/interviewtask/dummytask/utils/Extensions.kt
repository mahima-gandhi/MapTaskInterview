package com.interviewtask.dummytask.utils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.interviewtask.dummytask.model.MapListModel

const val MAP_LIST = "MAP_LIST"

// here we are saving markers list data in cache
fun saveMarkerListInLocal(list: ArrayList<MapListModel>?) {
    val gson = Gson()
    val jsonText: String = gson.toJson(list)
    Preferences.prefs?.saveValue(MAP_LIST, jsonText)
}

fun Fragment.toast(msg: String) {
    Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
}

// here we are getting markers list from local cache
fun getMarkerListFromLocal(): ArrayList<MapListModel> {
    val gson = Gson()
    val list: ArrayList<MapListModel> = ArrayList()
    val jsonText: String = Preferences.prefs?.getValue(MAP_LIST, "") ?: ""
    val text = gson.fromJson(
        jsonText,
        Array<MapListModel>::class.java
    )
    if (!text.isNullOrEmpty()) {
        list.addAll(text)
    }

    return list
}