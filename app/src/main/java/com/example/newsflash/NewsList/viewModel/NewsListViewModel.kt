package com.example.newsflash.NewsList.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.newsflash.Constants
import com.example.newsflash.NewsList.data.News
import com.example.newsflash.NewsList.data.NewsDataSource
import com.example.newsflash.NewsList.data.NewsDataSourceFactory
import com.example.newsflash.NewsList.data.State
import com.example.newsflash.NetworkService
import io.reactivex.disposables.CompositeDisposable

class NewsListViewModel : ViewModel() {

    private val networkService = NetworkService.getService(Constants.NEWS_LIST_BASE_URL)
    var newsList: LiveData<PagedList<News>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val newsDataSourceFactory: NewsDataSourceFactory =
            NewsDataSourceFactory(compositeDisposable, networkService)

    init {
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        newsList = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap(
            newsDataSourceFactory.newsDataSourceLiveData,
            NewsDataSource::state
    )

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}