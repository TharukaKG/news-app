package com.example.newsapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class MainViewModel(repository: MainRepository): ViewModel() {

    val loginStatus:LiveData<Result<Boolean>> = liveData{
        emitSource(repository.getLoginStatus().asLiveData())
    }
}
