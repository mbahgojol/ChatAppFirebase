package com.bpdsulteng.mobile.ui.login

import android.os.Bundle
import com.bpdsulteng.mobile.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.ActivityLoginBinding
import com.bpdsulteng.mobile.ui.base.BaseActivity
import com.bpdsulteng.mobile.ui.main.MainActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {
    private lateinit var binding: ActivityLoginBinding
    @Inject
    internal lateinit var viewModel: LoginViewModel

    override fun getBindingVariable() = BR.vmlogin
    override fun getLayoutId() = R.layout.activity_login
    override fun getViewModel() = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this
    }

    override fun onSuccessLogin() {
        startActivity(intentFor<MainActivity>().clearTask().newTask())
        finish()
    }

    override fun onErorrLogin(msg: String) {
        toast(msg)
    }
}
