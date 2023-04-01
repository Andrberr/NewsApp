package com.example.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Repository
import com.example.domain.models.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _liveData = MutableLiveData<List<News>>()
    val liveData: LiveData<List<News>> get() = _liveData

    private val compositeDisposable = CompositeDisposable()

    fun getNews(query: String) {
        val disposable = repository.getNewsList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { value -> _liveData.value = value }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}