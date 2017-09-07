package com.dynamicheart.raven.injection.module

import dagger.Module

/**
 * Created by jianbangyang on 2017/9/4.
 */
@Module(includes = arrayOf(ApiModule::class, DatabaseModule::class))
class DataModule {
}