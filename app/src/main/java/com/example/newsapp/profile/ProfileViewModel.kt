package com.example.newsapp.profile

import androidx.lifecycle.*
import com.example.newsapp.data.models.User
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository): ViewModel() {

    val loggedInUser:LiveData<Result<User>> = liveData {
        emitSource(repository.getCurrentUser().asLiveData())
    }

    fun updateSuccessLogout(user: User) = viewModelScope.launch{
        repository.updateLogoutSuccess(user)
    }

}
