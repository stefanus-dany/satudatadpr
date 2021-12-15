package com.satudata.services.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServer {

    companion object {
        const val baseURL = "http://sipilu.neomdesk.id/sipilu_mobile/"
    }

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): APIRequestData {
        return getRetrofitClient().create(APIRequestData::class.java)
    }
}