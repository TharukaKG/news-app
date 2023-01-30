package com.example.newsapp.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.main.MainViewModel
import com.example.newsapp.main.MainViewModelFactory
import com.example.newsapp.utils.onDestinationChanged
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: MainViewModelFactory
    lateinit var viewModel: MainViewModel
    lateinit var navController:NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.parent
        setContentView(view)

        initViewModel()
        getLoginStatus()

    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun getLoginStatus(){
        viewModel.loginStatus.observe(this as LifecycleOwner){ result ->
            setupNavigation()
            result.getOrNull()?.also { loggedIn->
                if (loggedIn){
                    navController.navigate(R.id.homeFragment)
                    binding.bottomNav.visibility = View.VISIBLE
                }else{
                    navController.navigate(R.id.loginFragment)
                    binding.bottomNav.visibility = View.GONE
                }
            }
        }
    }

    private fun setupNavigation(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.onDestinationChanged(binding.bottomNav)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation(){
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.favoritesFragment,
                R.id.profileFragment
            )
        )

        binding.bottomNav.setupWithNavController(navController)
    }

}