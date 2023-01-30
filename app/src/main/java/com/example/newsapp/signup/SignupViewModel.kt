package com.example.newsapp.signup

import androidx.lifecycle.*
import com.example.newsapp.data.models.User

class SignupViewModel(private val repository: SignupRepository):ViewModel() {

    fun createUser(user: User): LiveData<Result<Boolean>>{
        return liveData {
            emitSource(repository.createUser(user).asLiveData())
        }
    }

}
