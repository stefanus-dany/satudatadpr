package com.satudata.services.response.dashboard

import android.os.Parcelable
import com.satudata.services.model.AnggaranModel
import com.satudata.services.model.DPTModel
import com.satudata.services.model.PopulationModel
import com.satudata.services.model.ProvinceModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProvinceResponse(
    var code: Int = 0,
    var message: String = "",
    var data: List<ProvinceModel> = listOf(),
) : Parcelable