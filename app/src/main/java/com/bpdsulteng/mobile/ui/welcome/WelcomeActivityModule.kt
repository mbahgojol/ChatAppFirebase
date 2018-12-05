package com.bpdsulteng.mobile.ui.welcome

import dagger.Module
import dagger.Provides

/**
 * Created by knalb on 04/12/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Module
class WelcomeActivityModule {
    @Provides
    fun provideWelcomeViewModel(): WelcomeViewModel {
        return WelcomeViewModel()
    }

    @Provides
    fun provideWelcomeViewPagerAdapter(): WelcomeViewPagerAdapter {
        return WelcomeViewPagerAdapter()
    }
}