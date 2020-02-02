package com.aram.paexamtest

import android.app.Application
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArticlesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (INSTANCE == null){
            INSTANCE = this
        }
        startKoin {
            androidContext(this@ArticlesApplication)
            androidLogger()
            androidFileProperties()
            modules(koinModule)
        }
    }


    companion object{
        var INSTANCE : ArticlesApplication? = null

        fun isNetworkConnected(): Boolean{
            val cm = INSTANCE?.applicationContext?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
            val net = cm.activeNetworkInfo
            return net != null && net.isConnectedOrConnecting
        }
    }
}