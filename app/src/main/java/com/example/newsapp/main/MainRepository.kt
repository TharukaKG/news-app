package com.example.newsapp.main

import com.example.newsapp.data.local.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val userDao: UserDao) {

    fun getLoginStatus(): Flow<Result<Boolean>> {
        return flow {
            val users = userDao.getAllLoggedIn()
            if(users.isEmpty()) emit(Result.success(false))
            else emit(Result.success(true))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
