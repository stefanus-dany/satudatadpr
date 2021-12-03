package com.satudata.services.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RekapitulasiModel(
    var nama_provinsi: String = "",
    var nama_pemilu: String = "",
    var tahun: String = "",
    var total: Int = 0
) : Parcelable