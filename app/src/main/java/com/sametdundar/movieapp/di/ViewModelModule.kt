package com.sametdundar.movieapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(HomepageViewModel::class)
//    abstract fun bindHomepageViewModel(viewModel: HomepageViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ExpertOpinionViewModel::class)
//    abstract fun bindExpertOpinionViewModel(viewModel: ExpertOpinionViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ImportLocationViewModel::class)
//    abstract fun bindImportLocationViewModel(viewModel: ImportLocationViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ApplicationViewModelFactory): ViewModelProvider.Factory

}