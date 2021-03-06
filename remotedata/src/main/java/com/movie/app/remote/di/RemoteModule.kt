package com.movie.app.remote.di

import com.movie.app.remote.api.MovieApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class RemoteModule {
    @Provides
    @Singleton
    fun provideMovieApiApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson?): Retrofit {
        val httpURL = HttpUrl.Builder().scheme("http").host("omdbapi.com")
            .addQueryParameter("apikey", "dff87fd1").build()
        return Retrofit.Builder()
            .baseUrl("http://omdbapi.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        builder.setLenient()
        builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
            if (json.asJsonPrimitive.isNumber) {
                Date(json.asJsonPrimitive.asLong * 1000)
            } else {
                null
            }
        })
        return builder.create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(TokenInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }
}

class TokenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url().newBuilder().addQueryParameter("apikey", "dff87fd1").build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}