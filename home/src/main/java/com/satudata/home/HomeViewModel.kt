package com.satudata.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satudata.services.api.RetrofitServer
import com.satudata.services.model.BeritaModel
import com.satudata.services.response.home.BeritaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

//    val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    val _newsCategory = MutableLiveData<Int>().apply {
        value = 0
    }
    val newsCategory: LiveData<Int> = _newsCategory

    fun getBerita(): LiveData<List<BeritaModel>> {

        val mutableData = MutableLiveData<List<BeritaModel>>()
        var data: List<BeritaModel> = arrayListOf()

        viewModelScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getBerita()
                .enqueue(object : Callback<BeritaResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<BeritaResponse>,
                        response: Response<BeritaResponse>
                    ) {
                        if (response.isSuccessful) {
//                            for (i in response.body()?.data?.indices!!) {
//                                val namaProvinsi = response.body()!!.data[i].nama_provinsi
//                                val tahun = response.body()!!.data[i].tahun
//                                val total = response.body()!!.data[i].total.toString()
//                                val result: Array<String> = arrayOf(namaProvinsi, tahun, total)
                            val result = response.body()!!.data
                            data = result
//                            }
                            mutableData.value = data
                        } else Log.e("DataViewModel", "onResponse: ${response.errorBody()}")


                    }

                    override fun onFailure(call: retrofit2.Call<BeritaResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }
        return mutableData
    }
}