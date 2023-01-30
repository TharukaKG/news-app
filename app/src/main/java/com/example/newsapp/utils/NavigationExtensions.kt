package com.example.newsapp.utils

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import com.example.newsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

fun NavController.onDestinationChanged(bottomNavigationView: BottomNavigationView){
    addOnDestinationChangedListener{_, destination, _ ->
        if(destination.id == R.id.homeFragment||
            destination.id == R.id.favoritesFragment||
            destination.id == R.id.profileFragment){
            bottomNavigationView.visibility = View.VISIBLE
        }else{
            bottomNavigationView.visibility = View.GONE
        }
    }
}