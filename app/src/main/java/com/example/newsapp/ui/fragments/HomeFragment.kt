package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.home.HomeViewModel
import com.example.newsapp.home.HomeViewModelFactory
import com.example.newsapp.main.MainViewModel
import com.example.newsapp.main.MainViewModelFactory
import com.example.newsapp.ui.adapters.HeadlineRecyclerViewAdapter
import com.example.newsapp.ui.helpers.HeadLinesItemDecoration
import com.example.newsapp.ui.helpers.searchCategories
import com.example.newsapp.utils.EspressoIdlingResource
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val TAG = "HomeFragment"

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory:HomeViewModelFactory
    private lateinit var viewModel:HomeViewModel

    private lateinit var headlineRecyclerViewAdapter:HeadlineRecyclerViewAdapter
    private val headLinesItemDecoration:HeadLinesItemDecoration = HeadLinesItemDecoration(12)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        initViewModel()
        observeHeadlines()
        initSearchCategories()
        return binding?.parentHome
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun observeHeadlines(){
        viewModel.headlines.observe(viewLifecycleOwner){ result->
            result.getOrNull()?.also { list ->
                headlineRecyclerViewAdapter = HeadlineRecyclerViewAdapter(list)
                binding?.rvHeadlines?.initRecyclerView()
            }
        }
    }

    private fun RecyclerView.initRecyclerView() = binding?.apply{
        addItemDecoration(headLinesItemDecoration)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = headlineRecyclerViewAdapter
    }

    private fun initSearchCategories() = binding?.apply{
        cgCategory.removeAllViews()
        searchCategories.forEach { category->
            val chip = LayoutInflater.from(requireContext()).inflate(R.layout.chip_category, cgCategory, false) as Chip
            chip.text = category
            cgCategory.addView(chip)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}