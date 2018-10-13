package com.tcs.app.Api

import android.util.Base64
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class GithubInterceptor(val mail:String,val password:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val encrypt = "$mail:$password"
        val data = encrypt.toByteArray(charset("UTF-8"))
        val value:String = Base64.encodeToString(data,Base64.NO_WRAP)
        val keyValue = "Basic $value"
        val request = chain.request()
        Log.v("LogIn",keyValue)
        val url = request.url().newBuilder()
                .addQueryParameter("format", "json")
                .build()

        val newRequest = request.newBuilder()
                .url(url)
                .addHeader("Authorization", keyValue)
                .build()

        return chain.proceed(newRequest)
    }
}