package com.example.newsapp.home

import com.example.newsapp.BaseUnitTest
import com.example.newsapp.data.models.Headline
import com.example.newsapp.data.models.responses.HeadLineResponse
import com.example.newsapp.data.remote.NewsApi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response


class HomeRepositoryShould : BaseUnitTest() {

    lateinit var repository: HomeRepository
    private val api: NewsApi = mock(NewsApi::class.java)

    private val headlineList = mock(listOf<Headline>()::class.java)
    private val headLineResponse = HeadLineResponse("ok", totalResults = 36, headlineList)

    @Before
    fun setup() {
        repository = HomeRepository(api)
    }

    @Test
    fun callsToGetHeadLines() = runTest {
        api.getHeadlines()
        verify(api, times(1)).getHeadlines()
    }

    @Test
    fun returnsListOfHeadlines() = runTest {
        `when`(api.getHeadlines()).thenReturn(Response.success(headLineResponse))
        assertEquals(headlineList, repository.getLatestNews().first().getOrNull())
    }


}