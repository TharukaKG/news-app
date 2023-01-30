package com.example.newsapp.login

import androidx.lifecycle.*
import com.example.newsapp.data.models.User
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository):ViewModel() {

    fun login(userName:String, password:String): LiveData<Result<Boolean>> {
        return liveData{
            emitSource(repository.login(userName, password).asLiveData())
        }
    }

    fun updateLoginSuccess(user: User) = viewModelScope.launch {
        repository.updateLoginSuccess(user)
    }

}
