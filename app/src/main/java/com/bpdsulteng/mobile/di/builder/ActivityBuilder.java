package com.bpdsulteng.mobile.di.builder;

import com.bpdsulteng.mobile.ui.main.MainActivity;
import com.bpdsulteng.mobile.ui.main.MainActivityModule;
import com.bpdsulteng.mobile.ui.main.listdata.ListDataFragmentProvider;
import com.bpdsulteng.mobile.ui.welcome.WelcomeActivity;
import com.bpdsulteng.mobile.ui.welcome.WelcomeActivityModule;

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
            ListDataFragmentProvider.class})
    public abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {WelcomeActivityModule.class})
    public abstract WelcomeActivity bindWelcomeActivity();
}
