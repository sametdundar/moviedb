package com.sametdundar.movieapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sametdundar.movieapp.AppApplication
import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.base.fragment_ops.FragmentTransactionContainer
import com.sametdundar.movieapp.base.fragment_ops.INavigationManager
import com.sametdundar.movieapp.base.fragment_ops.TransactionType
import com.sametdundar.movieapp.di.Injectable
import com.sametdundar.movieapp.util.IDialogManager
import java.util.*
import kotlin.concurrent.schedule

abstract class BaseFragment : Injectable, Fragment() {

    protected var navigationManager: INavigationManager? = null
    private var dialog: IDialogManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navigationManager = initNavigationManager()
        dialog = (activity?.applicationContext as AppApplication).dialogManager
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        navigationManager = null
    }

    protected fun showFragment(
        manager: FragmentManager,
        container: FragmentTransactionContainer,
        fragment: Fragment
    ) {
        val transaction = manager.beginTransaction()
        if (container.isHasAnimation) {
            transaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
        }

        when (container.transactionType) {
            TransactionType.Add -> {
                transaction.add(container.layoutId, fragment, fragment.javaClass.name)
                transaction.replace(container.layoutId, fragment)
            }
            TransactionType.Replace -> transaction.replace(container.layoutId, fragment, fragment.javaClass.name)
        }
        if (container.isBackStack) {
            transaction.addToBackStack(container.backStackTag)
        }
        transaction.commitAllowingStateLoss()
    }

    private fun initNavigationManager(): INavigationManager? {
        return if (parentFragment != null && parentFragment is INavigationManager)
            parentFragment as INavigationManager
        else
            null
    }

    protected fun showLoading() {
        context?.run {
            dialog?.loading(this)
        }
    }

    protected fun dispatchLoading() {
        Timer("SettingUp", false).schedule(750) {
            dialog?.dispatchLoading()

        }
    }

}