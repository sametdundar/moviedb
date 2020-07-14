package com.sametdundar.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sametdundar.movieapp.api.Api
import com.sametdundar.movieapp.base.BaseResponse
import com.sametdundar.movieapp.base.pagination.MoviePagedListBuilderBaseResponse
import com.sametdundar.movieapp.datamanager.repositories.MovieRepository
import com.sametdundar.movieapp.model.TvListResultObject
import com.sametdundar.movieapp.util.ifNull
import javax.inject.Inject

class TvViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val api: Api,
    private val tvTopRatedPagedListBuilder: MoviePagedListBuilderBaseResponse<BaseResponse<List<TvListResultObject>>, TvListResultObject>,
    private val tvPopularPagedListBuilder: MoviePagedListBuilderBaseResponse<BaseResponse<List<TvListResultObject>>, TvListResultObject>
) : ViewModel() {

    private var tvTopRatedLivePagedListBuilder: LiveData<PagedList<TvListResultObject>>? = null
    private var tvPopularLivePagedListBuilder: LiveData<PagedList<TvListResultObject>>? = null


    val tvTopRated: LiveData<PagedList<TvListResultObject>>?
        get() {
            tvTopRatedLivePagedListBuilder.ifNull {
                tvTopRatedLivePagedListBuilder = initializedTvTopRatedPagedListBuilder().build()
            }
            return tvTopRatedLivePagedListBuilder
        }

    val tvPopular: LiveData<PagedList<TvListResultObject>>?
        get() {
            tvPopularLivePagedListBuilder.ifNull {
                tvPopularLivePagedListBuilder = initializedTvPopularPagedListBuilder().build()
            }
            return tvPopularLivePagedListBuilder
        }


    fun onFetchTvTopRate() {
        tvTopRatedLivePagedListBuilder = null
    }

    fun onFetchTvPopular(){
        tvPopularLivePagedListBuilder = null
    }


    private fun initializedTvTopRatedPagedListBuilder(): LivePagedListBuilder<Int, TvListResultObject> {
        return tvTopRatedPagedListBuilder.getPagedListLiveData(
            initialLoad = {api.fetchTvTopRated(null).execute()},
            afterLoad = {pageKey:Int -> api.fetchTvTopRated(pageKey).execute()}
        )
    }

    private fun initializedTvPopularPagedListBuilder(): LivePagedListBuilder<Int,TvListResultObject>{
        return tvPopularPagedListBuilder.getPagedListLiveData(
            initialLoad = {api.fetchTvPopular(null).execute()},
            afterLoad = {pageKey:Int -> api.fetchTvPopular(pageKey).execute()}
        )
    }


}