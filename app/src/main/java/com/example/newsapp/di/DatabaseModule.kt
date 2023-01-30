package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.UserDao
import com.example.newsapp.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideBlogDatabase(
        @ApplicationContext context: Context
    ): UserDatabase = Room.databaseBuilder(context, UserDatabase::class.java, "news_database")
            .build()

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase): UserDao =
        database.userDao()

}