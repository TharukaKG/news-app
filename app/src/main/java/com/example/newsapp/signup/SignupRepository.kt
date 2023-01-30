package com.example.newsapp.signup

import android.database.sqlite.SQLiteConstraintException
import com.example.newsapp.data.local.UserDao
import com.example.newsapp.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignupRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun createUser(user: User): Flow<Result<Boolean>> {
        return flow {
            val result = userDao.insertUser(user)
            if(result!=null) emit(Result.success(true))
        }.catch {
            emit(Result.failure(SQLiteConstraintException("Username already taken")))
        }
    }
}
