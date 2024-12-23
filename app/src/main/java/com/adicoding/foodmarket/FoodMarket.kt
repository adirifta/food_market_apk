package com.adicoding.foodmarket

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.adicoding.foodmarket.network.HttpClient

class FoodMarket : MultiDexApplication() {

    companion object {
        lateinit var instance: FoodMarket

        fun getApp(): FoodMarket {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences() : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token:String){
        getPreferences().edit().putString("PREF_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken():String? {
        return getPreferences().getString("PREF_TOKEN", null)
    }

    fun setUser(user:String){
        getPreferences().edit().putString("PREF_USER", user).apply()
        HttpClient.getInstance().buildRetrofitClient(user)
    }

    fun getUser():String? {
        return getPreferences().getString("PREF_USER", null)
    }
}