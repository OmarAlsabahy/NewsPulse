package com.example.newspulse.Api

import com.example.newspulse.Models.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getNews(@Query("apiKey")key:String, @Query("category")category:String):NewsModel

}