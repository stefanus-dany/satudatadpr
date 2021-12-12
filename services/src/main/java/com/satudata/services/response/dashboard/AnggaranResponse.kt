package com.satudata.services.response.dashboard

import android.os.Parcelable
import com.satudata.services.model.AnggaranModel
import com.satudata.services.model.DPTModel
import com.satudata.services.model.PopulationModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnggaranResponse(
    var code: Int = 0,
    var message: String = "",
    var data: List<AnggaranModel> = listOf(),
) : Parcelable