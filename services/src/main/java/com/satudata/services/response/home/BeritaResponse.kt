package com.satudata.services.response.home

import android.os.Parcelable
import com.satudata.services.model.BeritaModel
import com.satudata.services.model.DPTModel
import com.satudata.services.model.PopulationModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeritaResponse(
    var code: Int = 0,
    var message: String = "",
    var data: List<BeritaModel> = listOf(),
) : Parcelable