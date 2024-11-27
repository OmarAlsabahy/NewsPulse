package com.example.newspulse.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspulse.Repositories.GeneralRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(private val repo: GeneralRepo): ViewModel(){
    private val _news = MutableLiveData<NewsModel>()
    val news : LiveData<NewsModel>
        get() = _news
        private val _progress = MutableLiveData<Boolean>()
        val progress : LiveData<Boolean>
            get() = _progress

    fun getGeneralNews(){
        viewModelScope.launch (Dispatchers.IO){
            val result = viewModelScope.async (Dispatchers.IO){
                repo.getGeneralNews()
            }
            _progress.postValue(true)
            _news.postValue(result.await())
        }
    }
}