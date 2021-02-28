package com.example.newsflash

import com.example.newsflash.NewsList.data.State
import com.google.gson.JsonElement
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable


class ApiResponse(val status: State, @Nullable val data: JsonElement?, @Nullable error: Throwable?) {
    companion object {
        fun loading(): ApiResponse {
            return ApiResponse(State.LOADING, null, null)
        }

        fun success(@NonNull data: JsonElement): ApiResponse {
            return ApiResponse(State.DONE, data, null)
        }

        fun error(@NonNull error: Throwable): ApiResponse {
            return ApiResponse(State.ERROR, null, error)
        }
    }
}