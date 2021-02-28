package com.example.newsflash.Article.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsflash.ApiResponse
import com.example.newsflash.Constants
import com.example.newsflash.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LikesViewModel() : ViewModel() {

    val responseLiveData: MutableLiveData<ApiResponse> = MutableLiveData()
    private val networkService = NetworkService.getService(Constants.STATS_BASE_URL)
    private val compositeDisposable = CompositeDisposable()
    val data = MutableLiveData<String>()

    fun data(item: String?) {
        data.value = item
    }

    var activityLikesUrl: String = "likes/" + data.value.toString()

    var likesObservable = networkService.getLikes(activityLikesUrl)


    fun getLikes() {

        compositeDisposable.add(likesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { responseLiveData.setValue(ApiResponse.loading()) }
            .subscribe(
                { result -> responseLiveData.setValue(ApiResponse.success(result)) },
                { throwable -> responseLiveData.setValue(ApiResponse.error(throwable)) }
            ))


    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}





