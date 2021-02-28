package com.example.newsflash

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class PreferenceHelper(appContext: Context?) {

    private val sp1: SharedPreferences?
    private val editor1: SharedPreferences.Editor?
    val SHARED_PREFERENCE_NAME = "storeData"
    private val gson = Gson()


    init {

        sp1 = appContext?.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        editor1 = sp1?.edit()
    }

    fun clearAll() {
        editor1?.clear()
        editor1?.commit()
    }


    fun putBoolean(key: String, s: Boolean) {
        editor1?.putBoolean(key, s)
        editor1?.commit()
    }

    fun getBoolean(key: String): Boolean? {
        return sp1?.getBoolean(key, false)
    }

    fun putString(key: String, s: String?) {
        editor1?.putString(key, s)
        editor1?.commit()
    }


    fun setArticleId(string: String?) {
        editor1?.putString("ARTICLE ID", string)
        editor1?.commit()
    }

    fun getString(key: String): String? {
        return sp1?.getString(key, "")
    }

    fun getArticleId(): String? {
        return sp1?.getString("ARTICLE ID", "")
    }

    fun putLong(key: String, num: Long?) {
        if (num != null) {
            editor1?.putLong(key, num)
        }
        editor1?.commit()
    }

    fun getLong(key: String): Long? {
        return sp1?.getLong(key, 0L)
    }


    fun putInteger(key: String, s: Int?) {
        if (s != null) {
            editor1?.putInt(key, s)
            editor1?.commit()
        }

    }

    fun getInteger(key: String): Int? {
        return sp1?.getInt(key, 0)
    }
    
    

    fun putStringArrayList(key: String, s: ArrayList<String>?) {
        if (s != null) {
            val json = gson.toJson(s)
            editor1?.putString(key, json)
            editor1?.commit()
        }

    }

    fun getStringArrayList(key: String): ArrayList<String>? {
        val json = sp1?.getString(key, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type
        return gson.fromJson(json, type)
    }

    fun clearData(key: String) {
        editor1?.remove(key)
        editor1?.commit()
    }


    /*fun putLoginId(key: String, s: String?) {
        if (s != null) {
            editor1?.putString(key, s)
            editor1?.commit()
        }

    }

    fun getLoginId(key: String): String? {
        val str = sp1?.getString(key, null)
        return str
    }*/


    companion object {
        private var sPreferenceHelper: PreferenceHelper? = null

        operator fun get(c: Context): PreferenceHelper {
            if (sPreferenceHelper == null) {
                sPreferenceHelper = PreferenceHelper(c.applicationContext)
            }
            return sPreferenceHelper as PreferenceHelper
        }
    }


}