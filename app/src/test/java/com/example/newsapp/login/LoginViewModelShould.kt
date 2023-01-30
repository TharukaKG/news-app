package com.example.newsapp.login

import com.example.newsapp.BaseUnitTest
import com.example.newsapp.data.models.User
import com.example.newsapp.utils.getValueForTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class LoginViewModelShould: BaseUnitTest(){

    lateinit var viewModel: LoginViewModel
    val repository: LoginRepository = mock(LoginRepository::class.java)
    private val user:User = mock(User::class.java)

    val userName = "Tharuka"
    val password = "1234567"

    @Before
    fun setup(){
        viewModel = LoginViewModel(repository)
    }

    private fun mockSuccessFulCase() {
        `when`(repository.login(userName, password)).then {
            flow {
                emit(Result.success(true))
            }
        }
    }

    private fun mockFailingCase() {
        `when`(repository.login(userName, password)).then {
            flow {
                emit(Result.success(false))
            }
        }
    }

    @Test
    fun callLoginRepoToLogIn() = runTest {
        viewModel.login(userName, password).getValueForTest()
        verify(repository, times(1)).login(userName, password)
    }

    @Test
    fun emitTrueOnLoginSuccess() = runTest {
        mockSuccessFulCase()
        assertEquals(true, viewModel.login(userName, password).getValueForTest()?.getOrNull())
    }

    @Test
    fun emitFalseOnLoginNotSuccess() = runTest {
        mockFailingCase()
        assertEquals(false, viewModel.login(userName, password).getValueForTest()?.getOrNull())
    }

    @Test
    fun callUpdateUserOnSuccessfulLogin() = runTest{
        viewModel.updateLoginSuccess(user)
        verify(repository, times(1)).updateLoginSuccess(user)
    }

}