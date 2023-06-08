package com.demo.timestrends.api

import com.demo.timestrends.model.TimesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TimesAPI {

   
    @GET("svc/mostpopular/v2/shared/1/facebook.json")
    suspend fun getPopularArticles(@Query("api-key") apiKey: String): TimesResponse


}