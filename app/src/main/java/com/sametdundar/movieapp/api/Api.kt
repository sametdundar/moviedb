package com.sametdundar.movieapp.api

import com.sametdundar.movieapp.AppSettings
import com.sametdundar.movieapp.base.BaseResponse
import com.sametdundar.movieapp.model.MovieListResultObject
import com.sametdundar.movieapp.model.TvListResultObject
import retrofit2.Call
import retrofit2.http.GET
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


}