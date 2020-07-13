package com.sametdundar.movieapp.base

data class BaseResponse<out T>(
    val results: T,
    val total_results: Int?,
    val page: Int?,
    var total_pages: Int?
)