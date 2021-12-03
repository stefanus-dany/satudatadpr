package com.satudata.services.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(
    val username: String,
    val nama: String
) : Parcelable