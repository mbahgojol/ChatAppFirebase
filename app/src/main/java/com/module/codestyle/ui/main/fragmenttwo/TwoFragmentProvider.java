package com.module.codestyle.ui.main.fragmenttwo;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Module
public abstract class TwoFragmentProvider {
    @ContributesAndroidInjector(modules = {TwoFragmentModule.class})
    public abstract TwoFragment bindTwoFragmentFactory();
}
