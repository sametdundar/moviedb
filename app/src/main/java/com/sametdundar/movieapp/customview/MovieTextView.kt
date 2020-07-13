package com.sametdundar.movieapp.customview

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.sametdundar.movieapp.R

open class MovieTextView : AppCompatTextView {
    private lateinit var fontType: FontType

    constructor(context: Context?) : super(context) {
        init(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }


    private fun init(context: Context?, attrs: AttributeSet?) {
        context?.run {
            val typedArray = context.theme?.obtainStyledAttributes(attrs, R.styleable.View, 0, 0)
            fontType =
                FontType.values()[typedArray?.getInteger(R.styleable.View_textWeight, 0) ?: 0]
            val typeFace =
                CustomTypeFace.getTypeFace(context, fontType)
            typeface = typeFace
            typedArray?.recycle()
        }
    }

    fun setOnChangeFontType(fontType: FontType) {
        typeface = CustomTypeFace.getTypeFace(context, fontType)
    }

    private fun setTextDrawableColor(color: Int){
        for (compoundDrawable in this.compoundDrawables) {

            compoundDrawable?.colorFilter= PorterDuffColorFilter(ContextCompat.getColor(context,color),PorterDuff.Mode.SRC_IN)
        }

    }
}