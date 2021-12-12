package com.satudata.services.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnggaranModel(
    var nama_anggaran: String = "",
    var tahun: String = "",
    var total: Int = 0
) : Parcelable