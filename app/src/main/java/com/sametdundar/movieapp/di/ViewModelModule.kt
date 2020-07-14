package com.sametdundar.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sametdundar.movieapp.viewmodel.MovieViewModel
import com.sametdundar.movieapp.viewmodel.TvViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvViewModel::class)
    abstract fun bindTvViewModel(viewModel: TvViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ApplicationViewModelFactory): ViewModelProvider.Factory

}