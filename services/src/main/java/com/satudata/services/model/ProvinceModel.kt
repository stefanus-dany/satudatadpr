package com.satudata.services.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProvinceModel(
    var id_provinsi: Int = 0,
    var nama_provinsi: String = "",
    var longitude: Double = 0.0,
    var latitude: Double = 0.0,
    var density: Double = 0.0
) : Parcelable