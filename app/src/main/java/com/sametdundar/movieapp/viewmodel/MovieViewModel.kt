package com.sametdundar.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sametdundar.movieapp.api.Api
import com.sametdundar.movieapp.base.BaseResponse
import com.sametdundar.movieapp.base.pagination.MoviePagedListBuilderBaseResponse
import com.sametdundar.movieapp.datamanager.repositories.MovieRepository
import com.sametdundar.movieapp.model.MovieListResultObject
import com.sametdundar.movieapp.util.ifNull
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val api: Api,
    private val movieTopRatedPagedListBuilder: MoviePagedListBuilderBaseResponse<BaseResponse<List<MovieListResultObject>>, MovieListResultObject>,
    private val movieNowPlayingPagedListBuilder: MoviePagedListBuilderBaseResponse<BaseResponse<List<MovieListResultObject>>, MovieListResultObject>,
    private val moviePopularPagedListBuilder: MoviePagedListBuilderBaseResponse<BaseResponse<List<MovieListResultObject>>, MovieListResultObject>
) : ViewModel() {

    private var moviesTopRatedLivePagedListBuilder: LiveData<PagedList<MovieListResultObject>>? = null
    private var moviesNowPlayingLivePagedListBuilder: LiveData<PagedList<MovieListResultObject>>? = null
    private var moviesPopularLivePagedListBuilder: LiveData<PagedList<MovieListResultObject>>? = null


    val moviesTopRated: LiveData<PagedList<MovieListResultObject>>?
        get() {
            moviesTopRatedLivePagedListBuilder.ifNull {
                moviesTopRatedLivePagedListBuilder = initializedMovieTopRatedPagedListBuilder().build()
            }
            return moviesTopRatedLivePagedListBuilder
        }

    val moviesNowPlaying: LiveData<PagedList<MovieListResultObject>>?
        get() {
            moviesNowPlayingLivePagedListBuilder.ifNull {
                moviesNowPlayingLivePagedListBuilder = initializedMovieNowPlayingPagedListBuilder().build()
            }
            return moviesNowPlayingLivePagedListBuilder
        }

    val moviesPopular: LiveData<PagedList<MovieListResultObject>>?
        get() {
            moviesPopularLivePagedListBuilder.ifNull {
                moviesPopularLivePagedListBuilder = initializedMoviePopularPagedListBuilder().build()
            }
            return moviesPopularLivePagedListBuilder
        }


    fun onFetchMovieTopRate() {
        moviesTopRatedLivePagedListBuilder = null
    }

    fun onFetchMovieNowPlaying(){
        moviesNowPlayingLivePagedListBuilder = null
    }

    fun onFetchMoviePopular(){
        moviesPopularLivePagedListBuilder = null
    }

    private fun initializedMovieTopRatedPagedListBuilder(): LivePagedListBuilder<Int,MovieListResultObject>{
        return movieTopRatedPagedListBuilder.getPagedListLiveData(
            initialLoad = {api.fetchMovieTopRated(null).execute()},
            afterLoad = {pageKey:Int -> api.fetchMovieTopRated(pageKey).execute()}
        )
    }

    private fun initializedMovieNowPlayingPagedListBuilder(): LivePagedListBuilder<Int,MovieListResultObject>{
        return movieNowPlayingPagedListBuilder.getPagedListLiveData(
            initialLoad = {api.fetchMovieNowPlaying(null).execute()},
            afterLoad = {pageKey:Int -> api.fetchMovieNowPlaying(pageKey).execute()}
        )
    }

    private fun initializedMoviePopularPagedListBuilder(): LivePagedListBuilder<Int,MovieListResultObject>{
        return moviePopularPagedListBuilder.getPagedListLiveData(
            initialLoad = {api.fetchMoviePopular(null).execute()},
            afterLoad = {pageKey:Int -> api.fetchMoviePopular(pageKey).execute()}
        )
    }


}