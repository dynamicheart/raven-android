package com.dynamicheart.courier.injection.module

import com.dynamicheart.courier.data.remote.CourierService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by dynamicheart on 21/8/2017.
 */
@Module
class ApiModule {

    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideCourierService(okHttpClient: OkHttpClient, gson: Gson): CourierService {
        return Retrofit.Builder()
                .client(okHttpClient)
                .build()
                .create(CourierService::class.java)
    }
}