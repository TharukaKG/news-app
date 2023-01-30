package com.example.newsapp.profile

import com.example.newsapp.BaseUnitTest
import com.example.newsapp.data.models.User
import com.example.newsapp.utils.getValueForTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class ProfileViewModelShould: BaseUnitTest() {

    private lateinit var viewModel: ProfileViewModel
    private val repository:ProfileRepository = mock(ProfileRepository::class.java)

    private val user: User = mock(User::class.java)

    @Before
    fun setup(){
        viewModel = ProfileViewModel(repository)
    }

    @Test
    fun callUpdateOnSuccessfulLogout() = runTest {
        viewModel.updateSuccessLogout(user)
        verify(repository, times(1)).updateLogoutSuccess(user)
    }

    @Test
    fun emitsLoggedInUser() = runTest {
        `when`(repository.getCurrentUser()).then {
            flow {
                emit(user)
            }
        }

        assertEquals(user, viewModel.loggedInUser.getValueForTest())

    }

}