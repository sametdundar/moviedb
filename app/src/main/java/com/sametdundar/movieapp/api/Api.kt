package com.sametdundar.movieapp.api

import androidx.lifecycle.LiveData
import com.sametdundar.movieapp.AppSettings
import com.sametdundar.movieapp.base.BaseResponse
import com.sametdundar.movieapp.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/top_rated")
    fun fetchMovieTopRated(@Query("page") page: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): Call<BaseResponse<List<MovieListResultObject>>>

    @GET("movie/now_playing")
    fun fetchMovieNowPlaying(@Query("page") page: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): Call<BaseResponse<List<MovieListResultObject>>>

    @GET("movie/popular")
    fun fetchMoviePopular(@Query("page") page: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): Call<BaseResponse<List<MovieListResultObject>>>

    @GET("tv/top_rated")
    fun fetchTvTopRated(@Query("page") page: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): Call<BaseResponse<List<TvListResultObject>>>

    @GET("tv/popular")
    fun fetchTvPopular(@Query("page") page: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): Call<BaseResponse<List<TvListResultObject>>>

    @GET("movie/{movie_id}")
    fun fetchMovieDetail(@Path("movie_id") movie_id: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): LiveData<ApiResponse<MovieDetailResponse>>

    @GET("tv/{tv_id}")
    fun fetchTvDetail(@Path("tv_id") movie_id: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): LiveData<ApiResponse<TvReponse>>

    @GET("movie/{movie_id}/credits")
    fun fetchActorMovie(@Path("movie_id") movie_id: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): LiveData<ApiResponse<ActorResponse>>

    @GET("tv/{tv_id}/credits")
    fun fetchActorTv(@Path("tv_id") tv_id: Int?, @Query("api_key") api_key: String = AppSettings.API_KEY ): LiveData<ApiResponse<ActorResponse>>


}