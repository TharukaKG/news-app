package com.example.newsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.newsapp.data.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE isLoggedIn == 1")
    suspend fun getAllLoggedIn(): List<User>

    @Query("SELECT * FROM users WHERE username == :userName AND password == :password")
    suspend fun getUser(userName:String, password:String): List<User>

    @Insert
    suspend fun insertUser(user: User): Long?

    @Update
    suspend fun updateSuccessLogin(user: User)

    @Update
    suspend fun updateSuccessLogout(user: User)

}