package com.example.newsflash.NewsList.view.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsflash.*
import com.example.newsflash.NewsList.data.News
import com.example.newsflash.Article.view.ArticleActivity
import com.example.newsflash.databinding.ItemNewsBinding


class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    lateinit var articleID: String

    fun bind(news: News?) {
        if (news != null) {
            binding.authorName.text = news.author
            binding.publishedDate.text = news.publishedAt
            binding.newsTitle.text = news.title
            binding.newsDescription.text = news.description


            if (!news.urlToImage.isNullOrEmpty()) {
                Glide.with(binding.imgNewsBanner.context)
                    .load(news.urlToImage)
                    .into(binding.imgNewsBanner)
            }




            binding.cardView.setOnClickListener(View.OnClickListener {

                if(!news.url.isNullOrEmpty()){
                    articleID = Utils.replace(news.url)
                    PreferenceHelper(binding.cardView.context).putString("ARTICLE ID", articleID)
                }

                val intent = Intent(binding.cardView.context, ArticleActivity::class.java)
                intent.putExtra("ARTICLE URL", news.url)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                binding.cardView.context.startActivity(intent)
            })


        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemNewsBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_news,
                parent, false
            )
            return NewsViewHolder(binding)
        }
    }
}