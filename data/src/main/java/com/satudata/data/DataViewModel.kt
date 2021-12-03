package com.satudata.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.satudata.services.api.RetrofitServer
import com.satudata.services.response.data.DPTResponse
import com.satudata.services.response.data.PopulationResponse
import com.satudata.services.response.data.RekapitulasiResponse
import de.codecrafters.tableview.TableView
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class DataViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is notifications Fragment"
//    }

//    val text: LiveData<String> = _text

    fun getPopulation(): LiveData<MutableList<Array<String>>> {

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
                                data.add(result)
                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}", )


                    }

                    override fun onFailure(call: retrofit2.Call<PopulationResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getRekapitulasi(): LiveData<MutableList<Array<String>>> {

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
                                val result: Array<String> = arrayOf(namaProvinsi, namaPemilu, tahun, total)
                                data.add(result)
                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}", )


                    }

                    override fun onFailure(call: retrofit2.Call<RekapitulasiResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

    fun getDPT(): LiveData<MutableList<Array<String>>> {

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
                                data.add(result)
                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}", )


                    }

                    override fun onFailure(call: retrofit2.Call<DPTResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }

}