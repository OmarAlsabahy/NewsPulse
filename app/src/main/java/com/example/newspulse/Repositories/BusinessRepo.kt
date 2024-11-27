package com.example.newspulse.Repositories

import android.content.Context
import com.example.newspulse.Api.NewsApi
import com.example.newspulse.Enums.Category
import com.example.newspulse.Models.NewsModel
import com.example.newspulse.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BusinessRepo @Inject constructor(private val newsApi: NewsApi , @ApplicationContext val context:Context) {
    suspend fun getBusinessNews(): NewsModel {

        return newsApi.getNews(context.getString(R.string.apiKey), Category.business.toString())
    }
}