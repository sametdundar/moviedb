package com.sametdundar.movieapp.datamanager.repositories

import androidx.lifecycle.LiveData
import com.sametdundar.movieapp.api.Api
import com.sametdundar.movieapp.api.ApiResponse
import com.sametdundar.movieapp.api.NetworkServiceWrapper
import com.sametdundar.movieapp.api.Resource
import com.sametdundar.movieapp.base.AppExecutors
import com.sametdundar.movieapp.base.BaseResponse
import com.sametdundar.movieapp.base.ConnectionManager
import com.sametdundar.movieapp.model.ActorResponse
import com.sametdundar.movieapp.model.MovieDetailResponse
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

    fun getMovieDetail(id:Int) : LiveData<Resource<MovieDetailResponse>>{
        return object:
            NetworkServiceWrapper<MovieDetailResponse,MovieDetailResponse>(appExecutors,connectionManager){
            override fun loadFromApi(data: MovieDetailResponse): LiveData<MovieDetailResponse> {
                return InitialLiveData.create(data)
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> = api.fetchMovieDetail(id)

        }.asLiveData()
    }

    fun getActor(id:Int):LiveData<Resource<ActorResponse>>{
        return object:
            NetworkServiceWrapper<ActorResponse,ActorResponse>(appExecutors,connectionManager){
            override fun loadFromApi(data: ActorResponse): LiveData<ActorResponse> {
                return InitialLiveData.create(data)
            }

            override fun createCall(): LiveData<ApiResponse<ActorResponse>> = api.fetchActorMovie(id)

        }.asLiveData()
    }

}