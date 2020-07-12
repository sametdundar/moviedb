package com.sametdundar.movieapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sametdundar.movieapp.customview.NavigationBar
import com.sametdundar.movieapp.ui.FullScreenContainer
import com.sametdundar.movieapp.ui.navigationtab.MoviesFragmentTabContainer
import com.sametdundar.movieapp.ui.navigationtab.ProfileFragmentTabContainer
import com.sametdundar.movieapp.ui.navigationtab.TvFragmentTabContainer

private const val PAGE_COUNT = 3


class NavigationPagerAdapter(
    fm: FragmentManager,
    behavior: Int
) : FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment =
        when (position) {
            NavigationBar.MOVIES_PAGE -> MoviesFragmentTabContainer.newInstance()
            NavigationBar.TV_PAGE -> TvFragmentTabContainer.newInstance()
            NavigationBar.PROFILE_PAGE -> ProfileFragmentTabContainer.newInstance()
            else -> throw RuntimeException("Illegal position exception")
        }


    override fun getCount(): Int = PAGE_COUNT

    fun getClassName(position: Int) = when (position) {
        NavigationBar.MOVIES_PAGE -> MoviesFragmentTabContainer::class.java.name
        NavigationBar.TV_PAGE -> TvFragmentTabContainer::class.java.name
        NavigationBar.PROFILE_PAGE -> ProfileFragmentTabContainer::class.java.name
        NavigationBar.FULL_SCREEN_INDEX -> FullScreenContainer::class.java.name
        else -> throw RuntimeException("Illegal position exception")
    }
}