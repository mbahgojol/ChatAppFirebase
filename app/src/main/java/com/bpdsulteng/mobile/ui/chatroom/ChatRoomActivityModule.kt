package com.bpdsulteng.mobile.ui.chatroom

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ChatRoomActivityModule {
    @Provides
    fun provideChatRoomViewModel() = ChatRoomViewModel()

    @Provides
    fun provideChatRoomAdapter(context: Context) = ChatRoomAdapter(context)
}