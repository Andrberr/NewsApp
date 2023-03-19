package com.example.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.News
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _newsLiveData = MutableLiveData<List<News>>()
    val newsLiveData: LiveData<List<News>> get() = _newsLiveData

    fun getNewsList(category: String){
        viewModelScope.launch {
            _newsLiveData.value = repository.getNewsList(true, category)
        }
    }
}