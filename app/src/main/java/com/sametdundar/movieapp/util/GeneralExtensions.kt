package com.sametdundar.movieapp.util

import android.content.Context
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide


fun Any?.ifNull(f: () -> Unit) {
    if (this == null)
        f()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
fun hideKeyboardFrom(context: Context?, windowToken: IBinder?) {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun ImageView.loadFromURL(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .fitCenter()
        .into(this)
}
