package com.dynamicheart.raven.injection.module

import com.dynamicheart.raven.data.remote.RavenService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by dynamicheart on 21/8/2017.
 *
 */
@Module
class ApiModule {

    companion object {
        @JvmStatic private val ENDPOINT = "http://106.15.226.61:8080/"
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain
                    .request()
                    .newBuilder()
                    .head()
                    .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(tokenInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideRavenService(okHttpClient: OkHttpClient, gson: Gson): RavenService {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RavenService::class.java)
    }
}