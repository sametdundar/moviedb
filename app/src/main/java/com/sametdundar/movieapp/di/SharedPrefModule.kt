package com.sametdundar.movieapp.di

import android.content.Context
import android.content.SharedPreferences
import com.sametdundar.movieapp.datamanager.ISharedPref
import com.sametdundar.movieapp.datamanager.SharedPref
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class SharedPrefModule {
    companion object {
        private const val SHARED_PREFERENCES = "shared_preferences"
    }

    @Named(SHARED_PREFERENCES)
    @Provides
    fun provideBaseUrl(): String = "sametdundar.moviedb"

    @Singleton
    @Provides
    fun provideSharedPrefenreces(@ApplicationContext context: Context,
                                 @Named(SHARED_PREFERENCES) sharedPref: String
    ): SharedPreferences {
        return context.getSharedPreferences(sharedPref, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideSharedPref(sharedPref: SharedPref): ISharedPref {
        return sharedPref
    }
}