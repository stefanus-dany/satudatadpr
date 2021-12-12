package com.satudata.services.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeritaModel(
    var id_berita: Int = 0,
    var kategori_berita: String = "",
    var judul_berita: String = "",
    var isi_berita: String = "",
    var gambar_berita: String = "",
    var source: String = ""
) : Parcelable