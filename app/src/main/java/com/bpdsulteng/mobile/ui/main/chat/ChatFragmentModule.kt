package com.bpdsulteng.mobile.ui.main.chat

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ChatFragmentModule {
    @Provides
    fun provideChatViewModel() = ChatViewModel()

    @Provides
    fun provideUserAdapter(context: Context) = UserAdapter(context, true)
}