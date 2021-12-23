package com.satudata.adammalik.ui.authentication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.satudata.adammalik.R
import com.satudata.adammalik.databinding.FragmentLoginBinding
import com.satudata.profile.ProfileFragment.Companion.PROFIL_EMAIL
import com.satudata.profile.ProfileFragment.Companion.PROFIL_NAME
import com.satudata.profile.ProfileFragment.Companion.PROFIL_USERNAME
import com.satudata.services.api.RetrofitServer
import com.satudata.services.response.login.LoginResponse
import com.satudata.views.extensions.setSafeOnClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var profilName = ""
    private var profilUsername = ""
    private var profileEmail = ""

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesDetailUser: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        sharedPreferences = this.activity?.getSharedPreferences(
            "sharedPrefs",
            Context.MODE_PRIVATE
        ) as SharedPreferences
        val emailSaved = sharedPreferences.getString(CHECK_EMAIL, null)
        val passSaved = sharedPreferences.getString(CHECK_PASSWORD, null)
        val rememberMe = sharedPreferences.getBoolean(REMEMBER_ME, false)

        binding.etEmail.setText(emailSaved)
        binding.etPassword.setText(passSaved)
        binding.rememberMe.isChecked = rememberMe

        sharedPreferencesDetailUser = this.activity?.getSharedPreferences(
            "sharedPrefsDetailUser",
            Context.MODE_PRIVATE
        ) as SharedPreferences

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
            getLoginData()

        }

        return binding.root
    }

    private fun getLoginData() {
        lifecycleScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.login(
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()?.response == true) {
                                profilName = response.body()!!.payload.nama
                                profilUsername = response.body()!!.payload.username
                                profileEmail = response.body()!!.payload.email
                                findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                                binding.progressBar.visibility = View.GONE
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

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        val editor = sharedPreferences.edit()
        if (binding.rememberMe.isChecked) {
            editor.putString(CHECK_EMAIL, binding.etEmail.text.toString())
            editor.putString(CHECK_PASSWORD, binding.etPassword.text.toString())
            editor.putBoolean(REMEMBER_ME, true)
            editor.apply()
        } else editor.clear().apply()

        val editorDetailUser = sharedPreferencesDetailUser.edit()
        editorDetailUser.putString(PROFIL_NAME, profilName)
        editorDetailUser.putString(PROFIL_USERNAME, profilUsername)
        editorDetailUser.putString(PROFIL_EMAIL, profileEmail)
        editorDetailUser.apply()
    }

    companion object {
        const val CHECK_EMAIL = "check_email"
        const val CHECK_PASSWORD = "check_password"
        const val REMEMBER_ME = "remember_me"
    }

}