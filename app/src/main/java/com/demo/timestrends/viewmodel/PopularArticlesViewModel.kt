package com.demo.timestrends.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.demo.timestrends.api.TimesAPI
import com.demo.timestrends.model.TimesArticle
import com.demo.timestrends.model.TimesResponse
import com.demo.timestrends.util.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PopularArticlesViewModel @Inject constructor(private val api: TimesAPI) : ViewModel() {

    var popularArticlesFlow: Flow<List<TimesArticle>> = flow {
        val popularArticles = getPopularArticles()
        emit(popularArticles)
    }

    val errorFlow: MutableStateFlow<String> = MutableStateFlow("")
    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    private suspend fun getPopularArticles(): List<TimesArticle> {

        return try {
            val response = api.getPopularArticles(API_KEY)
            Log.d("PopularArticlesViewModel", "Popular Articles API call SUCCESS")
            Log.d("PopularArticlesViewModel", "Results ->  + ${response.toString()}")
            isLoading.emit(false)
            response.results
        } catch (t: Throwable) {
            Log.d("PopularArticlesViewModel", "Popular Articles API call FAILURE")
            Log.d("PopularArticlesViewModel", "Error making API call " + t.stackTraceToString())
            isLoading.emit(false)
            t.message?.let { errorFlow.emit(it) }

            emptyList()
        }
    }

}
