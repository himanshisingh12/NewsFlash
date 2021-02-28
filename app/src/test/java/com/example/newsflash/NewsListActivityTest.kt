package com.example.newsflash


import android.os.Build
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.newsflash.Article.view.ArticleActivity
import com.example.newsflash.NewsList.view.NewsListActivity
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class, sdk = [Build.VERSION_CODES.O_MR1])
class NewsListActivityTest{
    private lateinit var newsListActivity: NewsListActivity
    private lateinit var activityController: ActivityController<NewsListActivity>



    @Before
    @Throws(Exception::class)
    fun setUp() {
        activityController = Robolectric.buildActivity(NewsListActivity::class.java)
        newsListActivity = activityController.get()
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(newsListActivity)
    }


    @Test
    @Throws(java.lang.Exception::class)
    fun startNewActivity(){
        Robolectric.buildActivity(ArticleActivity::class.java)
    }

//    @Test
//    fun defaultDisplay() {
//        val currentRecyclerView = newsListActivity.findViewById<RecyclerView>(R.id.recycler_view)
//    }


}