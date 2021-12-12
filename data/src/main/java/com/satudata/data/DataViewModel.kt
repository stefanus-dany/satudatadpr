package com.satudata.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satudata.services.api.RetrofitServer
import com.satudata.services.response.data.DPTResponse
import com.satudata.services.response.data.GolputResponse
import com.satudata.services.response.data.PopulationResponse
import com.satudata.services.response.data.RekapitulasiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class DataViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is notifications Fragment"
//    }

//    val text: LiveData<String> = _text

    fun getPopulation(year: String): LiveData<MutableList<Array<String>>> {

        val mutableData = MutableLiveData<MutableList<Array<String>>>()
        val data: MutableList<Array<String>> = arrayListOf()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getEmployees()
                .enqueue(object : Callback<PopulationResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<PopulationResponse>,
                        response: Response<PopulationResponse>
                    ) {
                        if (response.isSuccessful) {
                            for (i in response.body()?.data?.indices!!) {
                                val namaProvinsi = response.body()!!.data[i].nama_provinsi
                                val tahun = response.body()!!.data[i].tahun
                                val total = response.body()!!.data[i].total.toString()
                                val result: Array<String> = arrayOf(namaProvinsi, tahun, total)
                                if (tahun == year) {
                                    data.add(result)
                                }
                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")


                    }

                    override fun onFailure(call: retrofit2.Call<PopulationResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getRekapitulasi(year: String, category: String): LiveData<MutableList<Array<String>>> {

        val mutableData = MutableLiveData<MutableList<Array<String>>>()
        val data: MutableList<Array<String>> = arrayListOf()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getRekapitulasi()
                .enqueue(object : Callback<RekapitulasiResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<RekapitulasiResponse>,
                        response: Response<RekapitulasiResponse>
                    ) {
                        if (response.isSuccessful) {
                            for (i in response.body()?.data?.indices!!) {
                                val namaProvinsi = response.body()!!.data[i].nama_provinsi
                                val namaPemilu = response.body()!!.data[i].nama_pemilu
                                val tahun = response.body()!!.data[i].tahun
                                val total = response.body()!!.data[i].total.toString()
                                val result: Array<String> =
                                    arrayOf(namaProvinsi, namaPemilu, tahun, total)
                                if (tahun == year && namaPemilu == category) {
                                    data.add(result)
                                }
                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")


                    }

                    override fun onFailure(
                        call: retrofit2.Call<RekapitulasiResponse>,
                        t: Throwable
                    ) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getGolput(year: String, category: String): LiveData<MutableList<Array<String>>> {

        val mutableData = MutableLiveData<MutableList<Array<String>>>()
        val data: MutableList<Array<String>> = arrayListOf()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getGolput()
                .enqueue(object : Callback<GolputResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<GolputResponse>,
                        response: Response<GolputResponse>
                    ) {
                        if (response.isSuccessful) {
                            for (i in response.body()?.data?.indices!!) {
                                val namaProvinsi = response.body()!!.data[i].nama_provinsi
                                val namaPemilu = response.body()!!.data[i].nama_pemilu
                                val tahun = response.body()!!.data[i].tahun
                                val total = response.body()!!.data[i].total_golput.toString()
                                val result: Array<String> =
                                    arrayOf(namaProvinsi, namaPemilu, tahun, total)
                                if (tahun == year && namaPemilu == category) {
                                    data.add(result)
                                }
                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")


                    }

                    override fun onFailure(call: retrofit2.Call<GolputResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getDPT(year: String): LiveData<MutableList<Array<String>>> {

        val mutableData = MutableLiveData<MutableList<Array<String>>>()
        val data: MutableList<Array<String>> = arrayListOf()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getDPT()
                .enqueue(object : Callback<DPTResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<DPTResponse>,
                        response: Response<DPTResponse>
                    ) {
                        if (response.isSuccessful) {
                            for (i in response.body()?.data?.indices!!) {
                                val namaProvinsi = response.body()!!.data[i].nama_provinsi
                                val tahun = response.body()!!.data[i].tahun
                                val total = response.body()!!.data[i].total.toString()
                                val result: Array<String> = arrayOf(namaProvinsi, tahun, total)
                                if (tahun == year) {
                                    data.add(result)
                                }
                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")


                    }

                    override fun onFailure(call: retrofit2.Call<DPTResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

}