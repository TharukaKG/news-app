package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentProfileBinding
import com.example.newsapp.profile.ProfileViewModel
import com.example.newsapp.profile.ProfileViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory: ProfileViewModelFactory
    lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        initViewModel()
        logout()
        return binding?.parent
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
    }

    private fun logout() = _binding?.apply {
        tvLogout.setOnClickListener {
            viewModel.loggedInUser.observe(viewLifecycleOwner) { result->
                result?.getOrNull()?.also {
                    viewModel.updateSuccessLogout(it.copy(isLoggedIn = false))
                    findNavController().navigate(R.id.loginFragment)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}