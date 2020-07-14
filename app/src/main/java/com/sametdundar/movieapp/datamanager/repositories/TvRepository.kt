package com.sametdundar.movieapp.datamanager.repositories

import androidx.lifecycle.LiveData
import com.sametdundar.movieapp.api.Api
import com.sametdundar.movieapp.api.ApiResponse
import com.sametdundar.movieapp.api.NetworkServiceWrapper
import com.sametdundar.movieapp.api.Resource
import com.sametdundar.movieapp.base.AppExecutors
import com.sametdundar.movieapp.base.ConnectionManager
import com.sametdundar.movieapp.model.ActorResponse
import com.sametdundar.movieapp.model.TvReponse
import com.sametdundar.movieapp.util.livedata.InitialLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val api: Api,
    private val connectionManager: ConnectionManager
) {

    fun getTvDetail(id:Int):LiveData<Resource<TvReponse>>{
        return object :
            NetworkServiceWrapper<TvReponse,TvReponse>(appExecutors,connectionManager){
            override fun loadFromApi(data: TvReponse): LiveData<TvReponse> {
                return InitialLiveData.create(data)
            }

            override fun createCall(): LiveData<ApiResponse<TvReponse>> = api.fetchTvDetail(id)

        }.asLiveData()
    }

    fun getActorTv(id:Int):LiveData<Resource<ActorResponse>>{
        return object:
            NetworkServiceWrapper<ActorResponse, ActorResponse>(appExecutors,connectionManager){
            override fun loadFromApi(data: ActorResponse): LiveData<ActorResponse> {
                return InitialLiveData.create(data)
            }

            override fun createCall(): LiveData<ApiResponse<ActorResponse>> = api.fetchActorTv(id)

        }.asLiveData()
    }

}