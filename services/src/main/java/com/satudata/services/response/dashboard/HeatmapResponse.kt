package com.satudata.services.response.dashboard

import android.os.Parcelable
import com.satudata.services.model.DPTModel
import com.satudata.services.model.GolputModel
import com.satudata.services.model.HeatmapEntity
import com.satudata.services.model.PopulationModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeatmapResponse(
    var code: Int = 0,
    var message: String = "",
    var data: List<HeatmapEntity> = listOf(),
) : Parcelable