package com.sametdundar.movieapp.customview

import android.content.Context
import android.graphics.Typeface
import java.util.*

class CustomTypeFace {
    companion object {
        private val cache = Hashtable<String, Typeface>()
        fun getTypeFace(context: Context, fontType: FontType): Typeface? {
            val assetPath = when(fontType) {
                FontType.Regular -> "context.getString(R.string.path_roboto_regular)"
                FontType.Medium -> "context.getString(R.string.path_roboto_medium)"
                FontType.Bold -> "context.getString(R.string.path_roboto_bold)"
                FontType.Light -> "context.getString(R.string.path_roboto_light)"
                FontType.Italic -> "context.getString(R.string.path_roboto_italic)"
                FontType.BoldItalic ->" context.getString(R.string.path_roboto_bold_italic)"
            }
            val typeFace = Typeface.createFromAsset(context.assets, assetPath)
            synchronized(cache) {
                if (!cache.containsKey(assetPath)) {
                    try {
                        cache[assetPath] = typeFace
                    } catch (e: Exception) {
                        e.printStackTrace()
                        return typeFace
                    }
                }
                return cache[assetPath]
            }
        }
    }
}