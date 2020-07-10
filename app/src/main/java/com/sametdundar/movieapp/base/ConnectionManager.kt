package com.sametdundar.movieapp.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.sametdundar.movieapp.di.ApplicationContext
import com.sametdundar.movieapp.util.GeneralUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var activity: AppCompatActivity? = null
    private var activityStarted = false
    private var _requestList = mutableListOf<() -> Unit>()
    fun setActivity(activity: AppCompatActivity?) {
        this.activity = activity
    }

    fun hasConnection(req: () -> Unit): Boolean {
        val connection = checkConnection()
        if (!connection) {
            _requestList.add(req)
            activity?.let {
                if (!activityStarted) {
                    //TODO Net olmadığı durumda açılacak ekran
                    activityStarted = true
                }
            }
        }
        return connection
    }

    fun changeActivityState(state: Boolean) {
        activityStarted = state
    }

    fun checkConnection(): Boolean {
        return GeneralUtil.checkNetworkConnection(context)
    }

    fun clearRequests() {
        _requestList.clear()
    }

    fun requestList() = _requestList
}
