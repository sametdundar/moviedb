package com.sametdundar.movieapp

import android.app.Activity
import android.app.Application
import com.sametdundar.movieapp.di.AppInjector
import com.sametdundar.movieapp.util.DialogManager
import com.sametdundar.movieapp.util.IDialogManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AppApplication: Application(), HasActivityInjector {
    companion object {
    }
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    var dialogManager: IDialogManager? = null

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
        dialogManager = DialogManager()


    }


    override fun activityInjector() = dispatchingAndroidInjector
}