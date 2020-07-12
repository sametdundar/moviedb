package com.sametdundar.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sametdundar.movieapp.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_splash

    override fun initialize() {
        var mRunnable = Runnable {
            MainActivity.newInstance(this)
        }
        var mHandler = Handler()
        mHandler.postDelayed(mRunnable, 1000)

    }
}
