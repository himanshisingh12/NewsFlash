package com.example.newsflash.NewsList.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsflash.NewsList.view.adapter.NewsListAdapter
import com.example.newsflash.NewsList.data.News
import com.example.newsflash.NewsList.data.State
import com.example.newsflash.NewsList.viewModel.NewsListViewModel
import com.example.newsflash.R
import com.example.newsflash.databinding.ActivityNewsListBinding

class NewsListActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsListBinding
    private lateinit var viewModel: NewsListViewModel
    private lateinit var newsListAdapter: NewsListAdapter
    lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)

        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        initAdapter()
        initState()
    }

    private fun initAdapter() {
        newsListAdapter = NewsListAdapter { viewModel.retry() }
        binding.recyclerView.adapter = newsListAdapter
        viewModel.newsList.observe(this,
                Observer {
                    newsListAdapter.submitList(it)
                })
    }

    private fun initState() {
        binding.txtError.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            binding.progressBar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            binding.txtError.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                newsListAdapter.setState(state ?: State.DONE)
            }
        })
    }


}