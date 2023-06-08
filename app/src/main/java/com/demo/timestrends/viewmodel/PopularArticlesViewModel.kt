package com.demo.timestrends.viewmodel

import androidx.lifecycle.ViewModel
import com.demo.timestrends.BuildConfig
import com.demo.timestrends.api.TimesAPI
import com.demo.timestrends.model.TimesArticle
import com.demo.timestrends.util.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
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
            val response = api.getPopularArticles(BuildConfig.API_KEY)
            isLoading.emit(false)
            response.results
        } catch (t: Throwable) {
            isLoading.emit(false)
            t.message?.let { errorFlow.emit(it) }

            emptyList()
        }
    }

}
