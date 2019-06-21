package com.bpdsulteng.mobile.conn

import android.util.Log
import com.bpdsulteng.mobile.AppLoader
import com.google.gson.annotations.SerializedName
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by knalb on 02/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer Expert
 */
class ApiObserver(val callBack: (String) -> Unit,
                  var callBackError: (String, ResponseError) -> Unit = fun(_, _) {}) :
        Observer<String> {


    /**
     * Rest Api Code Tutorial https://www.restapitutorial.com/httpstatuscodes.html
     */

    private val TAG = "ApiObserver"
    private val stringErrorBody = "HTTP Response Error Body()"
    private val responseError = "HTTP Response Message()"
    private var responseBody = ""
    private var code = 0

    override fun onSubscribe(d: Disposable) {
        Log.e(TAG, "onSubscribe " + Thread.currentThread().name)
    }

    override fun onNext(s: String) {
        Log.e(TAG, "onNext: " + Thread.currentThread().name)
        Log.e(TAG, "Response: $s")
        responseBody = s
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "onError: " + e.message)
        if (e is HttpException) {
            val response = e.response()

            val resError = ResponseError()
            resError.protocol = response.raw().protocol().toString()
            resError.code = response.raw().code()
            resError.message = response.raw().message()
            resError.url = response.raw().request().url().toString()

            code = response.raw().code()

            // get Response
            AppLoader.log(c = javaClass.simpleName, m = responseError + response.raw())
            AppLoader.log(c = javaClass.simpleName, m = responseError + response.raw().challenges())
            Log.d(responseError, "" + resError.toString())
            Log.d(responseError, "" + response.toString())

            try {
                val responseErrorBody = response.errorBody()!!.string()

                // get Response Error Body
                AppLoader.log(c = javaClass.simpleName, m = responseError + responseErrorBody)

                callBackError(responseErrorBody, resError)
            } catch (i: IOException) {
                i.printStackTrace()
            }

        } else {
            try {
                e.message?.let { AppLoader.log(stringErrorBody, it) }
                e.message?.let { callBackError(it, ResponseError()) }
            } catch (e1: Exception) {
                e1.printStackTrace()
            }

        }
    }

    override fun onComplete() {
        Log.e(TAG, "onComplete: All Done! " + Thread.currentThread().name)
        callBack(responseBody)
    }
}

data class ResponseError(
        var protocol: String? = null,
        var code: Int = 0,
        var message: String? = null,
        var url: String? = null
)

data class MsgErrorBody(
        @SerializedName("path")
        var path: String? = null,

        @SerializedName("error")
        var error: String? = null,

        @SerializedName("message")
        var message: String? = null,

        @SerializedName("timestamp")
        var timestamp: String? = null,

        @SerializedName("status")
        var status: Int = 0
)