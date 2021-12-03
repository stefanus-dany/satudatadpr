package com.satudata.services.api

import com.satudata.services.response.data.PopulationResponse
import com.satudata.services.response.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIRequestData {

    @FormUrlEncoded
    @POST("sipilu/login_service.php")
    fun login(
        @Field("post_username") username: String,
        @Field("post_password") password: String
    ): Call<LoginResponse>

    @GET("sipilu/get_population.php")
    fun getEmployees(): Call<PopulationResponse>
}