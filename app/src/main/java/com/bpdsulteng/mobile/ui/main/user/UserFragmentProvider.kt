package com.bpdsulteng.mobile.ui.main.user

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFragmentProvider {
    @ContributesAndroidInjector(modules = [UserFragmentModule::class])
    abstract fun provideUserFragmentFactory(): UserFragment
}