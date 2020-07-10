package com.sametdundar.movieapp.datamanager

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(
    private val sharedPref: SharedPreferences
) : ISharedPref {
}