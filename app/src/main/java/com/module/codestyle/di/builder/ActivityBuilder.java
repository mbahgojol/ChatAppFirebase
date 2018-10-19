package com.module.codestyle.di.builder;

import com.module.codestyle.ui.main.MainActivity;
import com.module.codestyle.ui.main.MainActivityModule;
import com.module.codestyle.ui.main.fragmentone.OneFragmentProvider;
import com.module.codestyle.ui.main.fragmenttwo.TwoFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by knalb on 04/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {MainActivityModule.class,
            OneFragmentProvider.class,
            TwoFragmentProvider.class})
    public abstract MainActivity bindMainActivity();
}
