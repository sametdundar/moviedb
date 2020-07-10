package com.sametdundar.movieapp.api

import com.sametdundar.movieapp.api.Status.*


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T?): Resource<T> {
            return Resource(ERROR, data, message)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }

        fun <T> noInternetConnection(): Resource<T> {
            return Resource(NO_INTERNET_CONNECTION, null, null)
        }
    }
}