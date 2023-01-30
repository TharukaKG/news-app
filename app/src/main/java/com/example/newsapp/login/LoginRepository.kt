package com.example.newsapp.login

import com.example.newsapp.data.local.UserDao
import com.example.newsapp.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.RuntimeException

class LoginRepository @Inject constructor(private val userDao: UserDao) {
    fun login(userName: String, password: String): Flow<Result<Boolean>> {
        return flow {
            val users = userDao.getUser(userName, password)
            if(users.isEmpty()) emit(Result.success(false))
            else emit(Result.success(true))
        }.catch {
           emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

    suspend fun updateLoginSuccess(user: User) {
       userDao.updateSuccessLogin(user)
    }

}
