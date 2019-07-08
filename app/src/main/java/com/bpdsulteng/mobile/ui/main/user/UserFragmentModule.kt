package com.bpdsulteng.mobile.ui.main.user

import android.content.Context
import com.bpdsulteng.mobile.ui.main.chat.UserAdapter
import dagger.Module
import dagger.Provides

@Module
class UserFragmentModule {
    @Provides
    fun provideUserViewModel() = UserViewModel()

    @Provides
    fun provideUserAdapter(context: Context) = UserAdapter(context, false)
}
