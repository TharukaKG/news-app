package com.example.newsapp.signup

import com.example.newsapp.BaseUnitTest
import com.example.newsapp.data.models.User
import com.example.newsapp.utils.getValueForTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.*

class SignupViewModelShould: BaseUnitTest() {

    private lateinit var viewModel: SignupViewModel
    private val repository: SignupRepository = mock(SignupRepository::class.java)
    private val user:User = mock(User::class.java)

    @Test
    fun callRepoCreateUser() = runTest{
        viewModel = SignupViewModel(repository)
        viewModel.createUser(user).getValueForTest()
        verify(repository, times(1)).createUser(user)
    }

    @Test
    fun createUserSuccess() = runTest {
        viewModel = SignupViewModel(repository)

        `when`(repository.createUser(user)).then {
            flow {
                emit(Result.success(true))
            }
        }

        assertEquals(true, viewModel.createUser(user).getValueForTest()?.getOrNull())
    }


    @Test
    fun createUserFailed() = runTest {
        viewModel = SignupViewModel(repository)

        `when`(repository.createUser(user)).then {
            flow {
                emit(Result.success(false))
            }
        }

        assertEquals(false, viewModel.createUser(user).getValueForTest()?.getOrNull())
    }

}