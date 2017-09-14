package com.dynamicheart.raven.injection.module

import android.support.v4.app.Fragment
import com.dynamicheart.raven.injection.PerFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @PerFragment
    @Provides
    fun provideFragment(): Fragment {
        return fragment
    }
}