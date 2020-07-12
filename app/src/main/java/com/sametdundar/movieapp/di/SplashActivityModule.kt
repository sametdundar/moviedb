package com.sametdundar.movieapp.di

import com.sametdundar.movieapp.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashActivityModule {
    @ContributesAndroidInjector(modules = [FragmentsBuilderModule::class])
    abstract fun contributeSplashActivity(): SplashActivity
}
