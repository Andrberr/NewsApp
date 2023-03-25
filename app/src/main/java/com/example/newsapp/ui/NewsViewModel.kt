package com.example.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.News
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _newsLiveData = MutableLiveData<List<News>>()
    val newsLiveData: LiveData<List<News>> get() = _newsLiveData

    private val _errorLiveData = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean> get() = _errorLiveData

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

//    private val handler = CoroutineExceptionHandler { _, _ ->
//        viewModelScope.launch {
//            val newsList = repository.getNewsList(false, "apple")
//            if (newsList.isNotEmpty()) _newsLiveData.value = newsList
//            _errorLiveData.value = true
//            _loadingLiveData.value = false
//        }
//    }

    fun getNewsList() {
        _loadingLiveData.value = true
        _errorLiveData.value = false
        viewModelScope.launch {
            repository.getNewsListFlow("apple").collect{
                _newsLiveData.value = it
                _loadingLiveData.value = false
            }
        }
    }
}