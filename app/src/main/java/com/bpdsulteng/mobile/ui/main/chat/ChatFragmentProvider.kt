package com.bpdsulteng.mobile.ui.main.chat

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatFragmentProvider {
    @ContributesAndroidInjector(modules = [ChatFragmentModule::class])
    abstract fun provideChatFragmentFactory(): ChatFragment
}