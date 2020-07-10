package com.sametdundar.movieapp.util

import android.content.Context

interface IDialogManager {

    fun loading(context: Context)
    fun dispatchLoading()
}