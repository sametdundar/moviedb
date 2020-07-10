package com.sametdundar.movieapp.util

import android.content.Context
import com.sametdundar.movieapp.customview.LoadingDialog

class DialogManager :IDialogManager{

    private var dialog: LoadingDialog? = null

    override fun loading(context: Context) {
        try {
            dispatchLoading()
            dialog = LoadingDialog(context)
            dialog?.setCancelable(false)
            dialog?.show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun dispatchLoading() {
        try {
            dialog?.dismiss()
            dialog = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}