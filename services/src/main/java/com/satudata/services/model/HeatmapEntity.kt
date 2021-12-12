package com.satudata.services.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeatmapEntity(
    var id_provinsi : Int = 0,
    var nama_provinsi : String = "",
    var latitude : Double = 0.0,
    var longitude : Double = 0.0,
    var populasi :Int = 0,
    var dpt :Int = 0,
    var rekapitulasi :Int = 0,
    var nama_pemilu :String = "",
    var tahun :String = "",
    var total_golput :Int = 0

) : Parcelable