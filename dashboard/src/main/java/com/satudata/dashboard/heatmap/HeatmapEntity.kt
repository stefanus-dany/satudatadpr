package com.satudata.dashboard.heatmap

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeatmapEntity(
    var provinceId : Int = 0,
    var provinceName : String = "",
    var provinceData : Long = 0,
    var latitude : Double = 0.0,
    var longitude : Double = 0.0
) : Parcelable