package com.satudata.dashboard.heatmap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satudata.services.api.RetrofitServer
import com.satudata.services.model.*
import com.satudata.services.response.dashboard.HeatmapResponse
import com.satudata.services.response.dashboard.ProvinceResponse
import com.satudata.services.response.data.DPTResponse
import com.satudata.services.response.data.PopulationResponse
import com.satudata.services.response.data.RekapitulasiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.NumberFormat

class HeatmapViewModel : ViewModel() {

    val _lat = MutableLiveData<Double>().apply {
        //default value
        value = -5.082689
    }
    val lat: LiveData<Double> = _lat

    val _long = MutableLiveData<Double>().apply {
        //default value
        value = 112.101298
    }
    val long: LiveData<Double> = _long

    fun getProvince(): LiveData<List<ProvinceModel>> {

        val mutableData = MutableLiveData<List<ProvinceModel>>()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getProvince()
                .enqueue(object : Callback<ProvinceResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<ProvinceResponse>,
                        response: Response<ProvinceResponse>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()!!.data
                            mutableData.value = result
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
                    }

                    override fun onFailure(call: retrofit2.Call<ProvinceResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getHeatmap(year: String, category: String): LiveData<MutableList<HeatmapEntity>> {

        val mutableData = MutableLiveData<MutableList<HeatmapEntity>>()
        val data: MutableList<HeatmapEntity> = arrayListOf()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getHeatmap()
                .enqueue(object : Callback<HeatmapResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<HeatmapResponse>,
                        response: Response<HeatmapResponse>
                    ) {
                        if (response.isSuccessful) {
                            for (i in response.body()?.data?.indices!!) {
                                if (response.body()!!.data[i].tahun == year && response.body()!!.data[i].nama_pemilu == category){
                                    data.add(response.body()!!.data[i])
                                }
                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")


                    }

                    override fun onFailure(call: retrofit2.Call<HeatmapResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

//    fun getRekapitulasi(): LiveData<List<RekapitulasiModel>> {
//
//        val mutableData = MutableLiveData<List<RekapitulasiModel>>()
//
//        viewModelScope.launch(Dispatchers.Default) {
//            val api = RetrofitServer().getInstance()
//            api.getRekapitulasi()
//                .enqueue(object : Callback<RekapitulasiResponse> {
//                    override fun onResponse(
//                        call: retrofit2.Call<RekapitulasiResponse>,
//                        response: Response<RekapitulasiResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val result = response.body()!!.data
//                            mutableData.value = result
//                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
//
//                    }
//
//                    override fun onFailure(
//                        call: retrofit2.Call<RekapitulasiResponse>,
//                        t: Throwable
//                    ) {
//                        Log.e("tues", "onFailure: ${t.message}")
//                    }
//
//                })
//        }
//        return mutableData
//    }

//    fun getDPT(): LiveData<List<DPTModel>> {
//
//        val mutableData = MutableLiveData<List<DPTModel>>()
//
//        viewModelScope.launch(Dispatchers.Default) {
//            val api = RetrofitServer().getInstance()
//            api.getDPT()
//                .enqueue(object : Callback<DPTResponse> {
//                    override fun onResponse(
//                        call: retrofit2.Call<DPTResponse>,
//                        response: Response<DPTResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val result = response.body()!!.data
//                            mutableData.value = result
//                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
//
//                    }
//
//                    override fun onFailure(call: retrofit2.Call<DPTResponse>, t: Throwable) {
//                        Log.e("tues", "onFailure: ${t.message}")
//                    }
//
//                })
//        }
//        return mutableData
//    }

}