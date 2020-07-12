package com.sametdundar.movieapp.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View

interface INavigationBarItem {
    fun setSelected()

    fun setUnselected()

    fun setIcon(vararg icons: Drawable?)

    fun setTitle(title: String)

    fun init(context: Context?, attrs: AttributeSet?)

    fun setClickListener(listener: View.OnClickListener)
    fun confirmClick()
}