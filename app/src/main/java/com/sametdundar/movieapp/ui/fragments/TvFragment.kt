package com.sametdundar.movieapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sametdundar.movieapp.R
import com.sametdundar.movieapp.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class TvFragment : BaseFragment() {

    companion object {
        fun newInstance() = TvFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

}
