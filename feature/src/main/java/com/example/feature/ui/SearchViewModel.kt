package com.example.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Repository
import com.example.domain.models.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _liveData = MutableLiveData<List<News>>()
    val liveData: LiveData<List<News>> get() = _liveData

    private val compositeDisposable = CompositeDisposable()

    private val publishSubject =  PublishSubject.create<String>()

    init{
        observeSearch()
    }

    fun getSearchingNews(query: String) {
        publishSubject.onNext(query)
    }

    private fun observeSearch(){
        val disposable = publishSubject
            .debounce(1000L, TimeUnit.MILLISECONDS)
            .switchMapSingle {
                 repository.getNewsList(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                _liveData.value = it
            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}