package com.dynamicheart.courier.injection

import javax.inject.Scope

/**
 * Created by dynamicheart on 20/8/2017.
 */
/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the Activity to be memorised in the
 * correct component.
 */
@MustBeDocumented
@Scope
@Retention
annotation class PerActivity