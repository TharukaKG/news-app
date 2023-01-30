package com.example.newsapp.profile

import com.example.newsapp.BaseUnitTest
import com.example.newsapp.data.local.UserDao
import com.example.newsapp.data.models.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ProfileRepositoryShould: BaseUnitTest() {

    private lateinit var repository: ProfileRepository
    private val userDao:UserDao = mock(UserDao::class.java)

    private val user:User = mock(User::class.java)
    private val userList:List<User> = listOf(user)

    @Before
    fun setup(){
        repository = ProfileRepository(userDao)
    }

    @Test
    fun callToUpdateSuccessLogout() = runTest {
        repository.updateLogoutSuccess(user)
        verify(userDao, times(1)).updateSuccessLogout(user)
    }

    @Test
    fun callToGetLoggedInUser() = runTest {
        repository.getCurrentUser().first()
        verify(userDao, times(1)).getAllLoggedIn()
    }

    @Test
    fun emitsLoggedInUser() = runTest{
        `when`(userDao.getAllLoggedIn()).thenReturn(userList)
        assertEquals(user, repository.getCurrentUser().first().getOrNull())
    }


}