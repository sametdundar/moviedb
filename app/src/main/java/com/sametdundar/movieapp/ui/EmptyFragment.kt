package com.sametdundar.movieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.base.BaseFragment
import com.sametdundar.movieapp.base.fragment_ops.NavigationManager
import com.sametdundar.movieapp.di.Injectable
import javax.inject.Inject

class EmptyFragment: BaseFragment(), Injectable {

    @Inject
    lateinit var navManager: NavigationManager

    companion object {
        fun newInstance() = EmptyFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }
}