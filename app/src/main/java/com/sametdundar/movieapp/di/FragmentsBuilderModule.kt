package com.sametdundar.movieapp.di

import com.sametdundar.movieapp.ui.EmptyFragment
import com.sametdundar.movieapp.ui.FullScreenContainer
import com.sametdundar.movieapp.ui.InitTabContainer
import com.sametdundar.movieapp.ui.fragments.MovieAndTvDetailFragment
import com.sametdundar.movieapp.ui.fragments.MovieFragment
import com.sametdundar.movieapp.ui.fragments.ProfileFragment
import com.sametdundar.movieapp.ui.fragments.TvFragment
import com.sametdundar.movieapp.ui.navigationtab.MoviesFragmentTabContainer
import com.sametdundar.movieapp.ui.navigationtab.ProfileFragmentTabContainer
import com.sametdundar.movieapp.ui.navigationtab.TvFragmentTabContainer
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeEmptyFragment(): EmptyFragment

    @ContributesAndroidInjector
    abstract fun contributeFullScreenContainer(): FullScreenContainer

    @ContributesAndroidInjector
    abstract fun contributeInitTabContainer(): InitTabContainer

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragmentTabContainer(): MoviesFragmentTabContainer

    @ContributesAndroidInjector
    abstract fun contributeProfileFragmentTabContainer(): ProfileFragmentTabContainer

    @ContributesAndroidInjector
    abstract fun contributeTvFragmentTabContainer(): TvFragmentTabContainer

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeTvFragment(): TvFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieAndTvDetailFragment

}