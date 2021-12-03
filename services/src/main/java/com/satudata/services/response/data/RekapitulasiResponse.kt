package com.satudata.services.response.data

import android.os.Parcelable
import com.satudata.services.model.PopulationModel
import com.satudata.services.model.RekapitulasiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class RekapitulasiResponse(
    var code: Int = 0,
    var message: String = "",
    var data: List<RekapitulasiModel> = listOf(),
) : Parcelable