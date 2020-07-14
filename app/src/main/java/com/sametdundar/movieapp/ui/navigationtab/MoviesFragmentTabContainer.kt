package com.sametdundar.movieapp.ui.navigationtab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.base.fragment_ops.*
import com.sametdundar.movieapp.di.Injectable
import com.sametdundar.movieapp.ui.EmptyFragment
import com.sametdundar.movieapp.ui.FullScreenContainer
import com.sametdundar.movieapp.ui.fragments.MovieFragment
import com.sametdundar.movieapp.util.hideKeyboardFrom
import javax.inject.Inject

class MoviesFragmentTabContainer : StackOwnerFragment(), INavigationManager, Injectable {

    companion object {
        fun newInstance() = MoviesFragmentTabContainer()
    }

    @Inject
    lateinit var navManager: NavigationManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_movies_tab_container, container, false)
        showInitialFragment()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navManager.setConsumerName(MoviesFragmentTabContainer::class.java.name)
        Log.d("navManager", navManager.hashCode().toString())

    }

    override fun getInitialFragment(): Fragment = MovieFragment.newInstance()

    override fun getNavigationManager(): NavigationManager = navManager

    override fun getHostLayoutId(): Int = R.id.hostFragment

    override fun onReplace(target: Fragment, transactionType: TransactionType, isBackStack:Boolean) {
        hideKeyboardFrom(context, view?.windowToken)
        showFragment(
            childFragmentManager,
            FragmentTransactionContainer.Builder()
                .setLayoutId(getHostLayoutId())
                .setTransactionType(transactionType)
                .setHasAnimation(true)
                .setBackState(isBackStack, null)
                .build(), target
        )
    }

    override fun onReplaceFullScreen(target: Fragment, transactionType: TransactionType) {
        hideKeyboardFrom(context, view?.windowToken)
        navManager.setFullScreenConsumerName(FullScreenContainer::class.java.name)
        (navManager.activeFragment() as FullScreenContainer).onReplace(
            target,
            transactionType
        )
    }
}