package com.example.newsflash.Article.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsflash.ApiResponse
import com.example.newsflash.Article.data.CommentResponse
import com.example.newsflash.Article.data.LikesResponse
import com.example.newsflash.Article.viewModel.CommentsViewModel
import com.example.newsflash.Article.viewModel.LikesViewModel
import com.example.newsflash.Constants
import com.example.newsflash.NewsList.data.State
import com.example.newsflash.PreferenceHelper
import com.example.newsflash.R
import com.example.newsflash.databinding.ActivityArticleBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.gson.Gson
import com.google.gson.JsonElement


class ArticleActivity : AppCompatActivity() {

    lateinit var binding: ActivityArticleBinding
    private lateinit var commentsViewModel: CommentsViewModel
    private lateinit var likesViewModel: LikesViewModel
    lateinit var commentResponse: CommentResponse
    lateinit var likesResponse: LikesResponse
    lateinit var articleId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_article)
        val toolbar: MaterialToolbar = binding.toolbar.toolbar
        setSupportActionBar(toolbar)

        val articleUrl: String = intent.getStringExtra(Constants.KEY_URL)!!
        articleId = PreferenceHelper(this).getString(Constants.KEY_ID)!!

        comments()
        likes()
        setWebView(articleUrl)
        binding.progressBar.visibility = View.VISIBLE




    }

    private fun setWebView(articleUrl: String){
        val settings: WebSettings = binding.articleWebview.settings
        settings.javaScriptEnabled = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        binding.articleWebview.webChromeClient = WebChromeClient()
        binding.articleWebview.loadUrl(articleUrl)
        binding.progressBar.visibility = View.GONE
    }

    private fun comments() {
        commentsViewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)
        commentsViewModel.commentData(articleId)
        commentsViewModel.getComments()
        commentsViewModel.responseLiveData.observe(this, Observer {
            this.consumeCommentResponse(it)
        })
    }

    private fun likes(){
        likesViewModel = ViewModelProvider(this).get(LikesViewModel::class.java)
        likesViewModel.data(articleId)
        likesViewModel.getLikes()
        likesViewModel.responseLiveData.observe(this, Observer {
            this.consumeLikeResponse(it)
        })
    }


    /*
  * method to handle response
  * */
    private fun consumeCommentResponse(apiResponse: ApiResponse) {
        when (apiResponse.status) {
            State.LOADING -> {
                Log.d("TAG", "Loading")
            }

            State.DONE -> {
                renderCommentResponse(apiResponse.data)
            }

            State.ERROR -> {
                Toast.makeText(
                    this, resources.getString(R.string.error_toast_text),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun consumeLikeResponse(apiResponse: ApiResponse) {
        when (apiResponse.status) {
            State.LOADING -> {
                Log.d("TAG", "Loading")
            }
            State.DONE -> {
                renderLikeResponse(apiResponse.data)
            }

            State.ERROR -> {
                Toast.makeText(
                    this, resources.getString(R.string.error_toast_text),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    /*
 * method to handle success response
 * */
    private fun renderCommentResponse(response: JsonElement?) {
        val gson = Gson()
            commentResponse = gson.fromJson(response, CommentResponse::class.java)
        if(commentResponse.comments != null){
            binding.toolbar.toolbarComments.text = commentResponse.comments.toString()
        }
    }

    private fun renderLikeResponse(response: JsonElement?) {
        val gson = Gson()
        likesResponse = gson.fromJson(response, LikesResponse::class.java)
        if(likesResponse.likes != null){
            binding.toolbar.toolbarLike.text = likesResponse.likes.toString()
        }
    }





}




