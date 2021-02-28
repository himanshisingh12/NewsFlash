package com.example.newsflash.NewsList.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleSource {
    @SerializedName("id")
    @Expose
    var id: Any? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
}