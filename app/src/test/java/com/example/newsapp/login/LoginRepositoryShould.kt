package com.example.newsapp.login

import com.example.newsapp.BaseUnitTest
import com.example.newsapp.data.local.UserDao
import com.example.newsapp.data.models.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class LoginRepositoryShould : BaseUnitTest() {

    private lateinit var repository: LoginRepository
    private val userDao: UserDao = mock(UserDao::class.java)
    private val user:User = mock(User::class.java)
    private val users: List<User> = mock(listOf<User>()::class.java)

    private val userName = "tharuka"
    private val password = "1234567"

    @Before
    fun setup(){
        repository = LoginRepository(userDao)
    }

    private suspend fun mockSuccessfulCase() {
        `when`(userDao.getUser(userName, password)).thenReturn(users)
    }

    private suspend fun mockFailingCase() {
        `when`(userDao.getUser(userName, password)).thenReturn(emptyList())
    }

    private suspend fun mockErrorCase() {
        `when`(userDao.getUser(userName, password)).thenReturn(null)
    }

    @Test
    fun callGetUserByPasswordAndUserName() = runTest {
        repository.login(userName, password).firstOrNull()
        verify(userDao, times(1)).getUser(userName, password)
    }

    @Test
    fun emitsUserOnSuccessLogin() = runTest {
        mockSuccessfulCase()
        assertEquals(true, repository.login(userName, password).first().getOrNull())

    }

    @Test
    fun emitsUserOnFailedLogin() = runTest {
        mockFailingCase()
        assertEquals(false, repository.login(userName, password).first().getOrNull())

    }

    @Test
    fun throwsErrorOnException() = runTest {
        mockErrorCase()
        assertEquals(
            "Something went wrong",
            repository.login(userName, password).first().exceptionOrNull()?.message
        )
    }

    @Test
    fun callsToSaveSuccessLogin() = runTest{
        repository.updateLoginSuccess(user)
        verify(userDao, times(1)).updateSuccessLogin(user)
    }

}