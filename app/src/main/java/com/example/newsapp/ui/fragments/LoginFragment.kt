package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.models.User
import com.example.newsapp.databinding.FragmentLoginBinding
import com.example.newsapp.login.LoginViewModel
import com.example.newsapp.login.LoginViewModelFactory
import com.example.newsapp.utils.validate
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory:LoginViewModelFactory
    private lateinit var viewModel:LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)

        initViewModel()
        login()
        navigate()

        return binding?.parent
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    private fun login() = binding?.apply {
        btnLogin.setOnClickListener {

            if(!tilUsername.validate(etUsername) || !tilPassword.validate(etPassword)) return@setOnClickListener

            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()
            val user = User(userName, password, true)

            viewModel.login(userName, password).observe(viewLifecycleOwner){ result ->

                result.getOrNull()?.also { success->
                    if(success){
                        viewModel.updateLoginSuccess(user)
                        findNavController().navigate(R.id.homeFragment)
                    }else{
                        tvError.text = context?.getString(R.string.invalid_credentials)
                    }
                }

                result.exceptionOrNull()?.also { error ->
                    tvError.text = error.message
                }

            }
        }
    }

    private fun navigate() = binding?.apply{
        tvCreateAccount.setOnClickListener { findNavController().navigate(R.id.signupFragment) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}