package com.bpdsulteng.mobile.ui.start

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.ui.login.LoginActivity
import com.bpdsulteng.mobile.ui.main.MainActivity
import com.bpdsulteng.mobile.ui.register.RegisterActivity
import com.bpdsulteng.mobile.utils.goTo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        login goTo LoginActivity::class.java
        register goTo RegisterActivity::class.java
    }

    override fun onStart() {
        super.onStart()
        var firebaseUser = FirebaseAuth.getInstance().currentUser
        if (firebaseUser != null) {
            this@StartActivity goTo MainActivity::class.java
            finish()
        }
    }
}
