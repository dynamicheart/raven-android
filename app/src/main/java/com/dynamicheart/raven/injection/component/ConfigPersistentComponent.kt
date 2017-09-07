package com.dynamicheart.raven.injection.component

import com.dynamicheart.raven.injection.ConfigPersistent
import com.dynamicheart.raven.injection.module.ActivityModule
import dagger.Component

/**
 * Created by jianbangyang on 2017/9/4.
 */
@ConfigPersistent
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}