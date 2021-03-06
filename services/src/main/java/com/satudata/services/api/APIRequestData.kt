package com.satudata.services.api

import com.satudata.services.response.dashboard.AnggaranResponse
import com.satudata.services.response.dashboard.HeatmapResponse
import com.satudata.services.response.dashboard.ProvinceResponse
import com.satudata.services.response.data.DPTResponse
import com.satudata.services.response.data.GolputResponse
import com.satudata.services.response.data.PopulationResponse
import com.satudata.services.response.data.RekapitulasiResponse
import com.satudata.services.response.home.BeritaResponse
import com.satudata.services.response.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIRequestData {

    @FormUrlEncoded
    @POST("login_service.php")
    fun login(
        @Field("post_username") username: String,
        @Field("post_password") password: String
    ): Call<LoginResponse>

    @GET("get_province.php")
    fun getProvince(): Call<ProvinceResponse>

    @GET("get_population.php")
    fun getEmployees(): Call<PopulationResponse>

    @GET("get_rekapitulasi.php")
    fun getRekapitulasi(): Call<RekapitulasiResponse>

    @GET("get_golput.php")
    fun getGolput(): Call<GolputResponse>

    @GET("get_golput.php")
    fun getHeatmap(): Call<HeatmapResponse>

    @GET("get_dpt.php")
    fun getDPT(): Call<DPTResponse>

    @GET("get_anggaran.php")
    fun getAnggaran(): Call<AnggaranResponse>

    @GET("get_berita.php")
    fun getBerita(): Call<BeritaResponse>
}