package com.satudata.services.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GolputModel(
    var nama_provinsi: String = "",
    var nama_pemilu: String = "",
    var tahun: String = "",
    var total_golput: Int = 0
) : Parcelable