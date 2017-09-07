package com.dynamicheart.raven.injection.module

import android.app.Application
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

/**
 * Created by jianbangyang on 2017/9/4.
 *
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRealmConfiguration(application: Application): RealmConfiguration {
        Realm.init(application)
        return RealmConfiguration.Builder()
                .name("raven.realm")
                .build()
    }

    @Provides
    fun provideRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.getInstance(realmConfiguration)
    }
}