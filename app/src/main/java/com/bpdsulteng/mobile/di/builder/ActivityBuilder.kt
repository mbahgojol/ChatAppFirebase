package com.bpdsulteng.mobile.di.builder

import com.bpdsulteng.mobile.ui.login.LoginActivity
import com.bpdsulteng.mobile.ui.login.LoginActivityModule
import com.bpdsulteng.mobile.ui.main.MainActivity
import com.bpdsulteng.mobile.ui.main.MainActivityModule
import com.bpdsulteng.mobile.ui.main.chat.ChatFragmentProvider
import com.bpdsulteng.mobile.ui.register.RegisterActivity
import com.bpdsulteng.mobile.ui.register.RegisterActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by knalb on 04/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [RegisterActivityModule::class])
    abstract fun bindRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class,
        ChatFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity
}
