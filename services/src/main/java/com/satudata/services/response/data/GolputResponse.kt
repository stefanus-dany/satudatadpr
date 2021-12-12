package com.satudata.services.response.data

import android.os.Parcelable
import com.satudata.services.model.DPTModel
import com.satudata.services.model.GolputModel
import com.satudata.services.model.PopulationModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GolputResponse(
    var code: Int = 0,
    var message: String = "",
    var data: List<GolputModel> = listOf(),
) : Parcelable