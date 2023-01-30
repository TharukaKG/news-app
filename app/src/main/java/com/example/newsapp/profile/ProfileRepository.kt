package com.example.newsapp.profile

import com.example.newsapp.data.local.UserDao
import com.example.newsapp.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val userDao: UserDao){

    suspend fun updateLogoutSuccess(user: User) {
        userDao.updateSuccessLogout(user)
    }

    suspend fun getCurrentUser():Flow<Result<User>> {
        return flow {
            val users = userDao.getAllLoggedIn()
            emit(Result.success(users[0]))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
