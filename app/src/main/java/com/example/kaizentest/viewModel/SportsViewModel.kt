package com.example.kaizentest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kaizentest.model.Resource
import com.example.kaizentest.model.Sports
import com.example.kaizentest.repository.SportsRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SportsViewModel(private val repository: SportsRepository): ViewModel() {

    var sports = MutableLiveData<Resource<ArrayList<Sports>?>>()

    fun getSports(): LiveData<Resource<ArrayList<Sports>?>> {
        sports.value = Resource.loading(null)
        repository.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<ArrayList<Sports>> {
            override fun onSubscribe(d: Disposable) = Unit

            override fun onNext(t: ArrayList<Sports>) {
                sports.value = Resource.success(t)
            }

            override fun onError(e: Throwable) {
                sports.value = Resource.error(e.message, null)
            }

            override fun onComplete() = Unit

        })
        return sports
    }
}