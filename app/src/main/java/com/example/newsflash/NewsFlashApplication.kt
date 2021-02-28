package com.example.newsflash

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class NewsFlashApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: NewsFlashApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}