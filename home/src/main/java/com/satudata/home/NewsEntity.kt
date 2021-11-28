package com.satudata.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsEntity(
    var newsId : Int = 0,
    var newsImage : String = "",
    var newsTitle : String = "",
    var newsBody : String = ""
) : Parcelable