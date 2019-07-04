package com.bpdsulteng.mobile.ui.register

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class RegisterActivityModule {
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideRegisterViewModel(auth: FirebaseAuth) = RegisterViewModel(auth)
}