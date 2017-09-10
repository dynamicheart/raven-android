package com.dynamicheart.raven.injection.module

import dagger.Module

@Module(includes = arrayOf(ApiModule::class, DatabaseModule::class))
class DataModule {
}