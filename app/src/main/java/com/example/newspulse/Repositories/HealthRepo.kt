package com.example.newspulse.Repositories

import android.content.Context
import com.example.newspulse.Api.NewsApi
import com.example.newspulse.Enums.Category
import com.example.newspulse.Models.NewsModel
import com.example.newspulse.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HealthRepo @Inject constructor(private val api: NewsApi, @ApplicationContext private val context: Context) {
    suspend fun getHealthNews(): NewsModel {
        return api.getNews(context.getString(R.string.apiKey) , Category.health.toString())
    }
}