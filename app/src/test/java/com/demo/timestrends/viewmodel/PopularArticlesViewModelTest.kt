package com.demo.timestrends.viewmodel

import com.demo.timestrends.BuildConfig
import com.demo.timestrends.api.TimesAPI
import com.demo.timestrends.model.TimesResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PopularArticlesViewModelTest {
    private lateinit var viewModel: PopularArticlesViewModel
    private lateinit var timesAPI: TimesAPI

    @Before
    fun setup() {
        timesAPI = mock(TimesAPI::class.java)
        viewModel = PopularArticlesViewModel(timesAPI)
    }


    @Test
    fun `test getPopularArticles successful response`() = runTest {

        val response = TimesResponse(listOf())
        `when`(timesAPI.getPopularArticles(BuildConfig.API_KEY)).thenReturn(response)


        val flow = viewModel.popularArticlesFlow.first()


        assertEquals(0, flow.size)
    }

    @Test
    fun `test getPopularArticles API error`() = runTest {

        `when`(timesAPI.getPopularArticles(BuildConfig.API_KEY)).thenThrow(RuntimeException("API Error"))


        val flow = viewModel.popularArticlesFlow.first()


        assertEquals(0, flow.size)
        assertEquals("API Error", viewModel.errorFlow.first())
    }

    @Test
    fun `test isLoading state after getPopularArticles call`() = runTest {

        val response = TimesResponse(listOf())
        `when`(timesAPI.getPopularArticles(BuildConfig.API_KEY)).thenReturn(response)


        viewModel.popularArticlesFlow.first()


        assertEquals(false, viewModel.isLoading.first())
    }
}