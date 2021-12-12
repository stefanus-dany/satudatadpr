package com.satudata.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satudata.services.api.RetrofitServer
import com.satudata.services.model.AnggaranModel
import com.satudata.services.model.DPTModel
import com.satudata.services.model.HeatmapEntity
import com.satudata.services.response.dashboard.AnggaranResponse
import com.satudata.services.response.dashboard.HeatmapResponse
import com.satudata.services.response.data.DPTResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {


    fun getAnggaran(): LiveData<List<AnggaranModel>> {

        val mutableData = MutableLiveData<List<AnggaranModel>>()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getAnggaran()
                .enqueue(object : Callback<AnggaranResponse> {
                    override fun onResponse(
                        call: Call<AnggaranResponse>,
                        response: retrofit2.Response<AnggaranResponse>
                    ) {
                        if (response.isSuccessful) {
                            mutableData.value = response.body()?.data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")


                    }

                    override fun onFailure(call: Call<AnggaranResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getCountTotalData(year: String, category: String): LiveData<HeatmapEntity> {

        val mutableData = MutableLiveData<HeatmapEntity>()
        val data: MutableList<HeatmapEntity> = arrayListOf()
        val datas = HeatmapEntity()
        var countDataPopulasi = 0
        var countDataDPT = 0
        var countDataRekap = 0
        var countDataGolput = 0

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getHeatmap()
                .enqueue(object : Callback<HeatmapResponse> {
                    override fun onResponse(
                        call: Call<HeatmapResponse>,
                        response: Response<HeatmapResponse>
                    ) {
                        if (response.isSuccessful) {
                            for (i in response.body()?.data?.indices!!) {
                                if (response.body()!!.data[i].tahun == year && response.body()!!.data[i].nama_pemilu == category){
                                    data.add(response.body()!!.data[i])
                                }
                            }

                            for (i in data.indices){
                                val populasi = data[i].populasi
                                val dpt = data[i].dpt
                                val rekap = data[i].rekapitulasi
                                val golput = data[i].total_golput
                                countDataPopulasi += populasi
                                countDataDPT += dpt
                                countDataRekap += rekap
                                countDataGolput += golput
                                Log.i("datas", "countData: $populasi")
                            }

                            datas.populasi = countDataPopulasi
                            datas.dpt = countDataDPT
                            datas.rekapitulasi = countDataRekap
                            datas.total_golput = countDataGolput

                            Log.i("datas", "onResponse: $datas")

                            mutableData.value = datas
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")
                    }

                    override fun onFailure(call: Call<HeatmapResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getGolput(year: String, category: String): LiveData<MutableList<HeatmapEntity>> {

        val mutableData = MutableLiveData<MutableList<HeatmapEntity>>()
        val data: MutableList<HeatmapEntity> = arrayListOf()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getHeatmap()
                .enqueue(object : Callback<HeatmapResponse> {
                    override fun onResponse(
                        call: Call<HeatmapResponse>,
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

                    override fun onFailure(call: Call<HeatmapResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }
}