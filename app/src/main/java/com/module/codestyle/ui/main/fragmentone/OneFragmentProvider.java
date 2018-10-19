package com.module.codestyle.ui.main.fragmentone;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Module
public abstract class OneFragmentProvider {
    @ContributesAndroidInjector(modules = {OneFragmentModule.class})
    public abstract OneFragment provideOneFragmentFactory();
}
