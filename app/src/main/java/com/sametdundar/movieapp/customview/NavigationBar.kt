package com.sametdundar.movieapp.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.util.inflate

class NavigationBar : ConstraintLayout, View.OnClickListener {
    companion object {
        const val MOVIES_PAGE = 0
        const val TV_PAGE = 1
        const val PROFILE_PAGE = 2
        const val FULL_SCREEN_INDEX = 99
    }

    lateinit var movies: INavigationBarItem
    lateinit var tv: INavigationBarItem
    lateinit var profile: INavigationBarItem
    lateinit var currentSelectedItem: INavigationBarItem
    private var onClickListener: NavigationBarOnClickListener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        init()
    }

    override fun onClick(p0: View?) {
        if (p0 == currentSelectedItem) {
            dispatchReSelectEvent(p0)
            return
        }
        currentSelectedItem.setUnselected()
        currentSelectedItem = p0 as INavigationBarItem
        (p0 as INavigationBarItem).setSelected()
        dispatchEvent(p0)
    }

    private fun init() {
        inflate(R.layout.layout_navigation_bar, true)
        initFields()
        setClickListeners()
        movies.setSelected()
        currentSelectedItem = movies
    }

    private fun initFields() {
        movies = findViewById<NavigationBarItem>(R.id.moviesItem)
        tv = findViewById<NavigationBarItem>(R.id.tvItem)
        profile = findViewById<NavigationBarItem>(R.id.profileItem)
        setIcons()
        setTitles()
    }

    private fun setClickListeners() {
        movies.setClickListener(this)
        tv.setClickListener(this)
        profile.setClickListener(this)
    }

    private fun setIcons() {
        movies.setIcon(
            ContextCompat.getDrawable(context, R.drawable.ic_launcher_background),
            ContextCompat.getDrawable(context, R.drawable.ic_launcher_background)
        )
        tv.setIcon(
            ContextCompat.getDrawable(context, R.drawable.ic_launcher_background),
            ContextCompat.getDrawable(context, R.drawable.ic_launcher_background)
        )
        profile.setIcon(
            ContextCompat.getDrawable(context, R.drawable.ic_launcher_background),
            ContextCompat.getDrawable(context, R.drawable.ic_launcher_background)
        )
    }

    private fun setTitles() {
        movies.setTitle(resources.getString(R.string.navigation_home))
        tv.setTitle(resources.getString(R.string.navigation_social))
        profile.setTitle(resources.getString(R.string.navigation_bulletin))
    }

    fun setOnClickListener(listener: NavigationBarOnClickListener) {
        onClickListener = listener
    }

    private fun dispatchEvent(v: View) {
        when (v) {
            movies -> {
                onClickListener?.onItemSelected(MOVIES_PAGE)
            }
            tv -> {
                onClickListener?.onItemSelected(TV_PAGE)
            }
            profile -> {
                onClickListener?.onItemSelected(PROFILE_PAGE)
            }
        }
    }

    private fun dispatchReSelectEvent(v: View) {
        when (v) {
            movies -> onClickListener?.onItemReselected(MOVIES_PAGE)
            tv -> onClickListener?.onItemReselected(TV_PAGE)
            profile -> onClickListener?.onItemReselected(PROFILE_PAGE)
        }
    }

    fun changeActiveTab(position: Int) {
        when (position) {
            MOVIES_PAGE -> movies.confirmClick()
            TV_PAGE -> tv.confirmClick()
            PROFILE_PAGE -> profile.confirmClick()
        }
    }
}