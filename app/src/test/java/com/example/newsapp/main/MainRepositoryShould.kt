package com.example.newsapp.main

import com.example.newsapp.BaseUnitTest
import com.example.newsapp.data.local.UserDao
import com.example.newsapp.data.models.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.*

class MainRepositoryShould: BaseUnitTest() {

    private lateinit var repository:MainRepository
    private val userDao:UserDao = mock(UserDao::class.java)

    private val loggedInUsers:List<User> = mock(listOf<User>()::class.java)

    @Test
    fun callUserDaoGetAllLoggedIn() = runTest {
        repository = MainRepository(userDao)
        repository.getLoginStatus().first()
        verify(userDao, times(1)).getAllLoggedIn()
    }

    @Test
    fun emitTrueIfLoggedIn() = runTest {
        repository = MainRepository(userDao)
        `when`(userDao.getAllLoggedIn()).thenReturn(loggedInUsers)
        assertEquals(true, repository.getLoginStatus().first().getOrNull())
    }

    @Test
    fun emitFalseIfNotLoggedIn() = runTest {
        repository = MainRepository(userDao)
        `when`(userDao.getAllLoggedIn()).thenReturn(emptyList())
        assertEquals(false, repository.getLoginStatus().first().getOrNull())
    }

    @Test
    fun throwsErrorOnException() = runTest {
        repository = MainRepository(userDao)
        `when`(userDao.getAllLoggedIn()).thenReturn(null)
        assertEquals("Something went wrong", repository.getLoginStatus().first().exceptionOrNull()?.message)
    }

}