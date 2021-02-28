package com.example.newsflash

import com.example.newsflash.NewsList.data.Response
import com.google.gson.JsonElement
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface NetworkService {


    @GET("top-headlines?country=us&category=business&apiKey=8735d49f66ad423dbcf7cb0a3838285d")
    fun getNews(
            @Query("page") page: Int,
            @Query("pageSize") pageSize: Int
    ): Single<Response>

    @GET()
    fun getComments(
        @Url() articleId: String):
            Observable<JsonElement>


    @GET()
    fun getLikes( @Url() articleId: String): Observable<JsonElement>



    companion object {
        fun getService(baseUrl: String): NetworkService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(NetworkService::class.java)
        }


    }

}