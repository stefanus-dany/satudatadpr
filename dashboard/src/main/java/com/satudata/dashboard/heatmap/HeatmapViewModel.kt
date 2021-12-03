package com.satudata.dashboard.heatmap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HeatmapViewModel : ViewModel() {

    val _lat = MutableLiveData<Double>().apply {
        value = -5.082689
    }
    val lat: LiveData<Double> = _lat

    val _long = MutableLiveData<Double>().apply {
        value = 112.101298
    }
    val long: LiveData<Double> = _long

}