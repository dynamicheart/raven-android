package com.dynamicheart.raven.injection.module

import com.dynamicheart.raven.data.AccountManager
import com.dynamicheart.raven.util.realm.RealmString
import com.dynamicheart.raven.data.remote.RavenService
import com.dynamicheart.raven.util.gson.DateTypeAdapter
import com.dynamicheart.raven.util.gson.RealmStringListTypeAdapter
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
import java.util.*
import javax.inject.Singleton
import io.realm.RealmList
import com.google.gson.reflect.TypeToken



@Module
class ApiModule {

    companion object {
        @JvmStatic private val ENDPOINT = "http://106.15.226.61:8080/api/v1/"
        @JvmStatic private val HEADER_X_AUTH = "X-AUTH"
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain
                    .request()
                    .newBuilder()
                    .addHeader(HEADER_X_AUTH, AccountManager.token?.authentication?:"")
                    .build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateTypeAdapter())
                .registerTypeAdapter(object : TypeToken<RealmList<RealmString>>(){}.type, RealmStringListTypeAdapter())
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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