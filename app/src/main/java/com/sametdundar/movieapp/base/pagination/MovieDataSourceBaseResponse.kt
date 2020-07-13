package com.sametdundar.movieapp.base.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.google.gson.JsonParseException
import com.sametdundar.movieapp.base.AppExecutors
import com.sametdundar.movieapp.base.BaseResponse
import com.sametdundar.movieapp.base.ConnectionManager
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

class MovieDataSourceBaseResponse<response : BaseResponse<List<MoviePaginationResponse>>, listObject>(
    private val initialLoad: () -> Response<response>,
    private val afterLoad: (pageKey: Int) -> Response<response>,
    private val connectionManager: ConnectionManager,
    private val appExecutors: AppExecutors
) : PageKeyedDataSource<Int, listObject>() {
    private var pageKey: Int = 1
    private var paramsInitial: LoadInitialParams<Int>? = null
    private var callbackInitial: LoadInitialCallback<Int, listObject>? = null
    private var paramsLoad: LoadParams<Int>? = null
    private var callbackLoad: LoadCallback<Int, listObject>? = null
    val listSize by lazy {
        MutableLiveData(0)
    }

    private fun initial() {
        if (!connectionManager.checkConnection())
            return
        appExecutors.networkIO().execute {
            try {
                val response = initialLoad()
                response.body()?.let {
                    listSize.postValue((listSize.value ?: 0 ) + it.results.size)
                    callbackInitial?.onResult(it.results as MutableList<listObject>, null, (it.page)?.plus(1))
                }
            }catch (e: IOException) {
                e.printStackTrace()
                callbackInitial?.onResult(emptyList(), null, null)
            } catch (e: JsonParseException) {
                e.printStackTrace()
                callbackInitial?.onResult(emptyList(), null, null)
            }
        }
    }

    private fun after() {
        if (!connectionManager.checkConnection())
            return
        appExecutors.networkIO().execute {
            paramsLoad?.let { prAfter ->
                try {
                    val response = afterLoad(prAfter.key)
                    response.body()?.let {
                        listSize.postValue((listSize.value ?: 0 ) + it.results.size)
                        if (response.isSuccessful) {
                            pageKey = (it.page)?.plus(1)?:1
                            callbackLoad?.onResult(it.results as MutableList<listObject>, (it.page)?.plus(1))
                        }
                    }
                }catch (e: IOException) {
                    e.printStackTrace()
                    callbackLoad?.onResult(emptyList(), null)
                } catch (e: JsonParseException) {
                    e.printStackTrace()
                    callbackLoad?.onResult(emptyList(), null)
                }
            }
        }
    }


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, listObject>) {
        listSize.postValue(0)
        this.paramsInitial = params
        this.callbackInitial = callback
        if (connectionManager.hasConnection(::initial)) {
            try {
                val response = initialLoad()
                response.body()?.let {
                    if (response.isSuccessful) {
                        listSize.postValue((listSize.value ?: 0 ) + it.results.size)
                        pageKey = (it.page)?.plus(1)?:1
                        callback.onResult(it.results as MutableList<listObject>, null, (it.page)?.plus(1))
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                callback.onResult(emptyList(), null, null)
            } catch (e: JsonParseException) {
                e.printStackTrace()
                callback.onResult(emptyList(), null, null)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, listObject>) {
        this.paramsLoad = params
        this.callbackLoad = callback
        if (connectionManager.hasConnection(::after)) {
            try {
                val response = afterLoad(params.key)
                response.body()?.let {
                    if (response.isSuccessful) {
                        listSize.postValue((listSize.value ?: 0 ) + it.results.size)
                        pageKey = (it.page)?.plus(1)?:1
                        callback.onResult(it.results as MutableList<listObject>, (it.page)?.plus(1))
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                callback.onResult(emptyList(), null)
            } catch (e: JsonParseException) {
                e.printStackTrace()
                callback.onResult(emptyList(), null)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, listObject>) {
    }
}

class MovieDataSourceFactoryBaseResponse<response : BaseResponse<List<MoviePaginationResponse>>, listObject>(
    private val dataSource: MovieDataSourceBaseResponse<response, listObject>
) : DataSource.Factory<Int, listObject>() {
    override fun create(): DataSource<Int, listObject> {
        return dataSource
    }
}

@Singleton
class MoviePagedListBuilderBaseResponse<response : BaseResponse<List<MoviePaginationResponse>>, listObject> @Inject constructor(
    private val connectionManager: ConnectionManager,
    private val appExecutors: AppExecutors
) {
    fun getPagedListLiveData(
        initialLoad: () -> Response<response>,
        afterLoad: (Int) -> Response<response>
        ) = LivePagedListBuilder<Int, listObject>(
        MovieDataSourceFactoryBaseResponse(
            MovieDataSourceBaseResponse(
                initialLoad,
                afterLoad,
                connectionManager,
                appExecutors
            )
        ), MoviePagedListConfigBaseResponse
    )
}

private const val MOVIE_PAGE_SIZE = 1

val MoviePagedListConfigBaseResponse =
    PagedList.Config.Builder().setPageSize(MOVIE_PAGE_SIZE).setEnablePlaceholders(true).build()


