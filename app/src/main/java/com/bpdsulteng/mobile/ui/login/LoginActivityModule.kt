package com.bpdsulteng.mobile.ui.login

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideLoginViewModel(auth: FirebaseAuth) = LoginViewModel(auth)
}