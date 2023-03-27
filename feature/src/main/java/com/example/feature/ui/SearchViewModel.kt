package com.example.feature.ui

import android.app.appsearch.SearchResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Repository
import com.example.domain.models.News
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val trigger = MutableStateFlow("")
    fun setQuery(query: String) {
        trigger.value = query
    }

    val results: StateFlow<List<News>> = trigger
        .mapLatest { query ->
            delay(2000)
            repository.getNewsList(query)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )
}