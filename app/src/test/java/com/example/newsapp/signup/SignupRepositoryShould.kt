package com.example.newsapp.signup

import android.database.sqlite.SQLiteConstraintException
import com.example.newsapp.BaseUnitTest
import com.example.newsapp.data.local.UserDao
import com.example.newsapp.data.models.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.*

class SignupRepositoryShould: BaseUnitTest() {

    lateinit var repository: SignupRepository
    val userDao:UserDao = mock(UserDao::class.java)
    val user:User = mock(User::class.java)
    val userId:Long = 1001L

    @Test
    fun callCreateUserDao() = runTest {
        repository = SignupRepository(userDao)
        repository.createUser(user).firstOrNull()
        verify(userDao, times(1)).insertUser(user)
    }

    @Test
    fun createUserSuccess() = runTest {
        repository = SignupRepository(userDao)

        `when`(userDao.insertUser(user)).thenReturn(userId)

        assertEquals(true, repository.createUser(user).first().getOrNull())

    }

}