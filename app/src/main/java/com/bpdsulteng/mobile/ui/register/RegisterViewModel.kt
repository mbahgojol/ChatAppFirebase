package com.bpdsulteng.mobile.ui.register

import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterViewModel(var auth: FirebaseAuth) : BaseObservableViewModel<RegisterNavigator>() {
    private val username = ObservableField<String>()
    private val email = ObservableField<String>()
    private val password = ObservableField<String>()

    fun register() {
        when (true) {
            username.get().isNullOrEmpty() ||
                    email.get().isNullOrEmpty() ||
                    password.get().isNullOrEmpty() -> navigator.onErorrRegister("All fileds are required")
            password.get().toString().length < 6 -> navigator.onErorrRegister("password must be at least 6 characters")
            else -> {
                auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
                        .addOnCompleteListener { tast ->
                            if (tast.isSuccessful) {
                                var firebaseUser = auth.currentUser!!
                                var userID = firebaseUser.uid
                                var reference = FirebaseDatabase.getInstance().getReference("Users").child(userID)

                                var values = HashMap<String, String>().apply {
                                    put("id", userID)
                                    put("username", username.get()!!)
                                    put("imageURL", "default")
                                    put("status", "offline")
                                    put("search", username.get()!!.toLowerCase())
                                }

                                reference.setValue(values)
                                        .addOnCompleteListener { tast ->
                                            if (tast.isSuccessful) {
                                                navigator.onSuccessRegister()
                                            } else {
                                                Log.d("MessageErrorReference", tast.exception!!.message)
                                            }
                                        }

                            } else {
                                navigator.onErorrRegister("You can't register woth this email or password")
                                Log.d("MessageError", tast.exception?.message)
                            }
                        }
            }
        }
    }

    fun getUsername() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            username.set(p0.toString())
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