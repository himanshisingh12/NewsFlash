package com.example.newsflash.Article.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentResponse {
    @SerializedName("comments")
    @Expose
    var comments: Int? = null
    @SerializedName("likes")
    @Expose
    var likes: Int? = null
}