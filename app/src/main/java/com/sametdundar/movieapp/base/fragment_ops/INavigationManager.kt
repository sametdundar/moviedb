package com.sametdundar.movieapp.base.fragment_ops

import androidx.fragment.app.Fragment

interface INavigationManager {
    fun onReplace(target: Fragment, transactionType: TransactionType, isBackStack: Boolean = true)
    fun onReplaceFullScreen(target: Fragment, transactionType: TransactionType) {}
}