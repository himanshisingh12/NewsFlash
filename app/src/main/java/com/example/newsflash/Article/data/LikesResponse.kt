package com.example.newsflash.Article.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LikesResponse {
    @SerializedName("likes")
    @Expose
    var likes: Int? = null
}