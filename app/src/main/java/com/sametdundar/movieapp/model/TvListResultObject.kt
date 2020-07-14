package com.sametdundar.movieapp.model

import com.sametdundar.movieapp.base.pagination.MoviePaginationResponse
import java.io.Serializable

data class TvListResultObject(

    val poster_path: String,
    val popularity: Double,
    val id: Int,
    val backdrop_path: String,
    val vote_average: Double,
    val overview: String,
    val first_air_date: String,
    val origin_country: List<String>,
    val genre_ids: List<Int>,
    val original_language: String,
    val vote_count: Int,
    val name: String,
    val original_name: String
) : Serializable, MoviePaginationResponse()