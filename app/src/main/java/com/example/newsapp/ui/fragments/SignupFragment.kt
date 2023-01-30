package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.data.models.User
import com.example.newsapp.databinding.FragmentLoginBinding
import com.example.newsapp.databinding.FragmentSignupBinding
import com.example.newsapp.signup.SignupViewModel
import com.example.newsapp.signup.SignupViewModelFactory
import com.example.newsapp.utils.validate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_signup.*
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment: Fragment() {

    private var _binding:FragmentSignupBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var viewModelFactory: SignupViewModelFactory

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(layoutInflater)

        initViewModel()
        signUp()

        return binding?.parent
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this, viewModelFactory)[SignupViewModel::class.java]
    }

    private fun signUp() = binding?.apply{

        btnSignup.setOnClickListener {

            if(!tilUsername.validate(etUsername) || !tilPassword.validate(etPassword, true)) return@setOnClickListener

            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()

            User(userName = userName, password = password, true).apply {
                viewModel.createUser(this).observe(viewLifecycleOwner){ result->
                    if(result.getOrNull()!=null){
                        findNavController().navigate(R.id.homeFragment)
                    }else{
                        tvError.text = result.exceptionOrNull()?.message
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}