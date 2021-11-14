package com.satudata.adammalik.ui.authentication

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.satudata.adammalik.R
import com.satudata.adammalik.databinding.FragmentLoginBinding
import com.satudata.views.extensions.setSafeOnClickListener

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

            if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString().trim())
                    .matches()
            ) {
                binding.etEmail.error = "Please enter your valid email!"
                binding.etEmail.requestFocus()
                binding.progressBar.visibility = View.INVISIBLE
                return@setSafeOnClickListener
            }

            if (binding.etPassword.text.toString().trim().isEmpty()) {
                binding.etPassword.error = "Please enter your password!"
                binding.etPassword.requestFocus()
                binding.progressBar.visibility = View.INVISIBLE
                return@setSafeOnClickListener
            }

            if (binding.etEmail.text.toString() == "dany@dpr.go.id" && binding.etPassword.text.toString() == "aaaaaa"){
                findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
            } else {
                Toast.makeText(requireContext(), "Your email and password doesn't match", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE

        }



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}