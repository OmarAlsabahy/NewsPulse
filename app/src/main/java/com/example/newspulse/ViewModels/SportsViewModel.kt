package com.example.newspulse.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspulse.Models.NewsModel
import com.example.newspulse.Repositories.SportsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SportsViewModel @Inject constructor(private val repo: SportsRepo) : ViewModel() {
    private val _news = MutableLiveData<NewsModel>()
    val news : LiveData<NewsModel>
        get() = _news
    private val _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean>
        get() = _progress

    fun getSportsNews(){
        viewModelScope.launch (Dispatchers.IO){
            val result = viewModelScope.async(Dispatchers.IO) {
                repo.getSportsNews()
            }
            _progress.postValue(true)
            _news.postValue(result.await())
        }
    }
}