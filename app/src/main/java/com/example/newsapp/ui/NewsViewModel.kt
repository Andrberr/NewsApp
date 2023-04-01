package com.example.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Repository
import com.example.domain.models.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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

    private val compositeDisposable = CompositeDisposable()

    fun getNewsList() {
        var disposable = repository.getNewsFromApi("b")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        compositeDisposable.add(disposable)

        _loadingLiveData.value = true
        _errorLiveData.value = false
        disposable = repository.getNewsFromDataBase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                _errorLiveData.value = true
                _loadingLiveData.value = false
            }
            .subscribe {
                _newsLiveData.value = it
                _loadingLiveData.value = false
            }

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}