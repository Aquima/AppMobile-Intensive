package com.tcs.app.Api

import com.tcs.app.model.User
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {
    @GET("/user") fun authenticate():Observable<User>
    companion object {
        fun create(mail:String,password:String): ApiClient {
            val client = OkHttpClient().newBuilder()
                    .addInterceptor(GithubInterceptor(mail,password))
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        // level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
                    })
                    .build()

            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://api.github.com")
                    .build()

            return retrofit.create(ApiClient::class.java)
        }

    }
}
