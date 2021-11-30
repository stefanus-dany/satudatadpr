package com.satudata.adammalik.ui.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.satudata.adammalik.R
import com.satudata.adammalik.databinding.FragmentLoginBinding
import com.satudata.views.extensions.setSafeOnClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setSafeOnClickListener {

            binding.progressBar.visibility = View.VISIBLE

            if (binding.etEmail.text.toString().trim().isEmpty()) {
                binding.etEmail.error = "Please enter your email!"
                binding.etEmail.requestFocus()
                binding.progressBar.visibility = View.INVISIBLE
                return@setSafeOnClickListener
            }

//            if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString().trim())
//                    .matches()
//            ) {
//                binding.etEmail.error = "Please enter your valid email!"
//                binding.etEmail.requestFocus()
//                binding.progressBar.visibility = View.INVISIBLE
//                return@setSafeOnClickListener
//            }

            if (binding.etPassword.text.toString().trim().isEmpty()) {
                binding.etPassword.error = "Please enter your password!"
                binding.etPassword.requestFocus()
                binding.progressBar.visibility = View.INVISIBLE
                return@setSafeOnClickListener
            }

//            if (binding.etEmail.text.toString() == "dany@dpr.go.id" && binding.etPassword.text.toString() == "aaaaaa") {
//                findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
//            } else {
//                Toast.makeText(
//                    requireContext(),
//                    "Your email and password doesn't match",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
            getData()
            binding.progressBar.visibility = View.GONE

        }

        return binding.root
    }

    private fun getData() {
        val api = RetrofitClient().getInstance()
        api.login(binding.etEmail.text.toString().trim(), binding.etPassword.text.toString().trim()).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful) {
                    if (response.body()?.response == true) {
                        findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Login gagal, periksa kembali username dan password anda",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Login gagal, terjadi kesalahan!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("tues", "onFailure: ${t.message}", )
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

data class PayloadLogin(
    val username: String,
    val nama: String
)

class ResponseLogin(
    var response: Boolean,
    var payload: PayloadLogin
)

interface ApiClient {
    @FormUrlEncoded
    @POST("sipilu/login_service.php")
    fun login(
        @Field("post_username") username: String,
        @Field("post_password") password: String
    ): Call<ResponseLogin>
}

class RetrofitClient {
    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.13.10.72/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): ApiClient {
        return getRetrofitClient().create(ApiClient::class.java)
    }
}