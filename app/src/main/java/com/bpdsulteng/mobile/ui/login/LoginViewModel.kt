package com.bpdsulteng.mobile.ui.login

import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(var auth: FirebaseAuth) : BaseObservableViewModel<LoginNavigator>() {
    private val email = ObservableField<String>()
    private val password = ObservableField<String>()

    fun login() {
        if (email.get().isNullOrEmpty() || password.get().isNullOrEmpty()) {
            navigator.onErorrLogin("All fileds are required")
        } else {
            auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            navigator.onSuccessLogin()
                        } else {
                            Log.d("MessageError", task.exception?.message)
                            navigator.onErorrLogin("Authentication failed!")
                        }
                    }
        }
    }

    fun getEmail() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            email.set(p0.toString())
        }
    }

    fun getPassword() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            password.set(p0.toString())
        }
    }
}