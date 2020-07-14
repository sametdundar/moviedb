package com.sametdundar.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sametdundar.movieapp.api.Api
import com.sametdundar.movieapp.api.Resource
import com.sametdundar.movieapp.base.BaseResponse
import com.sametdundar.movieapp.base.pagination.MoviePagedListBuilderBaseResponse
import com.sametdundar.movieapp.datamanager.repositories.MovieRepository
import com.sametdundar.movieapp.datamanager.repositories.TvRepository
import com.sametdundar.movieapp.model.ActorResponse
import com.sametdundar.movieapp.model.MovieDetailResponse
import com.sametdundar.movieapp.model.TvListResultObject
import com.sametdundar.movieapp.model.TvReponse
import com.sametdundar.movieapp.util.ifNull
import javax.inject.Inject

class TvViewModel @Inject constructor(
    private val repository: TvRepository,
    private val api: Api,
    private val tvTopRatedPagedListBuilder: MoviePagedListBuilderBaseResponse<BaseResponse<List<TvListResultObject>>, TvListResultObject>,
    private val tvPopularPagedListBuilder: MoviePagedListBuilderBaseResponse<BaseResponse<List<TvListResultObject>>, TvListResultObject>
) : ViewModel() {

    private var tvTopRatedLivePagedListBuilder: LiveData<PagedList<TvListResultObject>>? = null
    private var tvPopularLivePagedListBuilder: LiveData<PagedList<TvListResultObject>>? = null

    private val _tvDetail = MutableLiveData<Int>()


    fun tvDetail(): LiveData<Resource<TvReponse>> =
        Transformations.switchMap(_tvDetail) {
            repository.getTvDetail(it)
        }

    fun tvActor(): LiveData<Resource<ActorResponse>> =
        Transformations.switchMap(_tvDetail) {
            repository.getActorTv(it)
        }

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

    fun onFetchTvDetail(id: Int) {
        if (_tvDetail.value != id) {
            _tvDetail.value = id
        }
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