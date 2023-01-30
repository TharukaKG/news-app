package com.example.newsapp.main

import com.example.newsapp.BaseUnitTest
import com.example.newsapp.utils.getValueForTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.*

class MainViewModelShould: BaseUnitTest() {

    private lateinit var viewModel: MainViewModel
    private val repository: MainRepository = mock(MainRepository::class.java)

    @Test
    fun callHomeRepoGetLoginStatus(){
        viewModel = MainViewModel(repository)
        viewModel.loginStatus.getValueForTest()
        verify(repository, times(1)).getLoginStatus()
    }

    @Test
    fun emitTrueIfUserLoggedIn() = runTest{
        viewModel = MainViewModel(repository)
        `when`(repository.getLoginStatus()).then {
            flow {
                emit(Result.success(true))
            }
        }

        assertEquals(true, repository.getLoginStatus().first().getOrNull())
    }

    @Test
    fun emitFalseIfUserLoggedOut() = runTest {
        viewModel = MainViewModel(repository)
        `when`(repository.getLoginStatus()).then {
            flow {
                emit(Result.success(false))
            }
        }
        assertEquals(false, repository.getLoginStatus().first().getOrNull())
    }

}