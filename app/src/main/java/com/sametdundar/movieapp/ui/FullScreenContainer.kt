package com.sametdundar.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.base.fragment_ops.*
import com.sametdundar.movieapp.di.Injectable
import com.sametdundar.movieapp.util.hideKeyboardFrom
import javax.inject.Inject

class FullScreenContainer : StackOwnerFragment(), INavigationManager, Injectable {
    companion object {
        fun newInstance() = FullScreenContainer()
    }

    @Inject
    lateinit var navManager: NavigationManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_full_screen, container, false)
        showInitialFragment()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun getInitialFragment(): Fragment {
        return EmptyFragment.newInstance()
    }

    override fun getNavigationManager(): NavigationManager {
        return navManager
    }

    override fun getHostLayoutId(): Int = R.id.fullScreenContainer

    override fun onReplace(target: Fragment, transactionType: TransactionType, isBackStack: Boolean) {
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
        showFragment(
            childFragmentManager,
            FragmentTransactionContainer.Builder()
                .setLayoutId(getHostLayoutId())
                .setTransactionType(transactionType)
                .setHasAnimation(true)
                .setBackState(true, null)
                .build(), target
        )
    }
}