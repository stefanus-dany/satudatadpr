package com.satudata.services.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopulationModel(
    var id_provinsi: String = "",
    var nama_provinsi: String = "",
    var tahun: String = "",
    var total: Int = 0
) : Parcelable