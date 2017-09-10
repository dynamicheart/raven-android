package com.dynamicheart.raven.injection.component

import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.injection.module.ActivityModule
import dagger.Component

@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}