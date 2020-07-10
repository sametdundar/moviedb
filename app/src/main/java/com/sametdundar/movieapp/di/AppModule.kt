package com.sametdundar.movieapp.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class])
abstract class AppModule {

    @Binds
    @ApplicationContext
    abstract fun bindContext(application: Application): Context
}