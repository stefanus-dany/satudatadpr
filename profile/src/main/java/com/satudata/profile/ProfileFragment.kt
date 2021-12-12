package com.satudata.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.satudata.profile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        sharedPreferences = this.activity?.getSharedPreferences(
            "sharedPrefsDetailUser",
            Context.MODE_PRIVATE
        ) as SharedPreferences

        val profileName = sharedPreferences.getString(PROFIL_NAME, null)
        val profileUsername = sharedPreferences.getString(PROFIL_USERNAME, null)
        val profileEmail = sharedPreferences.getString(PROFIL_EMAIL, null)

        binding.etFullname.setText(profileName)
        binding.etFullname.keyListener = null
        binding.etUsername.setText(profileUsername)
        binding.etUsername.keyListener = null
        binding.etEmail.setText(profileEmail)
        binding.etEmail.keyListener = null

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val PROFIL_NAME = "profil_name"
        const val PROFIL_USERNAME = "profil_username"
        const val PROFIL_EMAIL = "profil_email"
    }

}