package com.example.newspulse.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspulse.Models.NewsModel
import com.example.newspulse.Repositories.BusinessRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BusinessViewModel @Inject constructor(private val repo:BusinessRepo):ViewModel() {
    private val _news = MutableLiveData<NewsModel>()
    private val _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean>
        get() = _progress
    val news : LiveData<NewsModel>
        get() = _news
    fun getBusinessNews(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = viewModelScope.async(Dispatchers.IO) {
                repo.getBusinessNews()
            }
            _progress.postValue(true)
            _news.postValue(result.await())
        }
    }
}