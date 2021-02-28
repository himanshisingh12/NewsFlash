package com.example.newsflash.Article.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsflash.ApiResponse
import com.example.newsflash.Constants
import com.example.newsflash.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CommentsViewModel() : ViewModel() {

    val responseLiveData: MutableLiveData<ApiResponse> = MutableLiveData()
    private val networkService = NetworkService.getService(Constants.STATS_BASE_URL)
    private val compositeDisposable = CompositeDisposable()
    val data = MutableLiveData<String>()

    fun commentData(item: String?) {
        data.value = item
    }

    var activityCommentsUrl: String = "comments/" + data.value.toString()

    var commentsObservable = networkService.getComments(activityCommentsUrl)

    fun getComments() {

        compositeDisposable.add(commentsObservable
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





