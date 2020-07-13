package com.sametdundar.movieapp.datamanager.repositories

import androidx.lifecycle.LiveData
import com.sametdundar.movieapp.api.Api
import com.sametdundar.movieapp.api.ApiResponse
import com.sametdundar.movieapp.api.NetworkServiceWrapper
import com.sametdundar.movieapp.api.Resource
import com.sametdundar.movieapp.base.AppExecutors
import com.sametdundar.movieapp.base.BaseResponse
import com.sametdundar.movieapp.base.ConnectionManager
import com.sametdundar.movieapp.model.MovieListResultObject
import com.sametdundar.movieapp.util.livedata.InitialLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val api: Api,
    private val connectionManager: ConnectionManager
) {

//    fun getMovieTopRated(): LiveData<Resource<List<MovieListResultObject>>>{
//        return object :
//            NetworkServiceWrapper<List<MovieListResultObject>,BaseResponse<List<MovieListResultObject>>>(appExecutors,connectionManager){
//            override fun loadFromApi(data: BaseResponse<List<MovieListResultObject>>): LiveData<List<MovieListResultObject>> {
//                return InitialLiveData.create(data.results)
//            }
//
//            override fun createCall(): LiveData<ApiResponse<BaseResponse<List<MovieListResultObject>>>> = api.fetchMovieTopRated()
//
//        }.asLiveData()
//    }

}