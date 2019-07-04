package com.bpdsulteng.mobile.ui.register

import android.os.Bundle
import com.bpdsulteng.mobile.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.ActivityRegisterBinding
import com.bpdsulteng.mobile.ui.base.BaseActivity
import com.bpdsulteng.mobile.ui.main.MainActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import javax.inject.Inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(), RegisterNavigator {
    private lateinit var binding: ActivityRegisterBinding
    @Inject
    internal lateinit var viewModel: RegisterViewModel

    override fun getBindingVariable() = BR.vmregister
    override fun getLayoutId() = R.layout.activity_register
    override fun getViewModel() = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this
    }

    override fun onSuccessRegister() {
        startActivity(intentFor<MainActivity>().clearTask().newTask())
        finish()
    }

    override fun onErorrRegister(msg: String) {
        toast(msg)
    }
}
