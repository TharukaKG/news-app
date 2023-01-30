package com.example.newsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val userName:String,
    val password:String,
    val isLoggedIn:Boolean
)
