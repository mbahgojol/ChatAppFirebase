package com.bpdsulteng.mobile.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.bpdsulteng.mobile.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.ActivityMainBinding
import com.bpdsulteng.mobile.ui.base.BaseActivity
import com.bpdsulteng.mobile.ui.start.StartActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
        MainNavigator,
        HasSupportFragmentInjector {

    private lateinit var binding: ActivityMainBinding
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    internal lateinit var viewModel: MainViewModel

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector
    override fun getBindingVariable() = BR.vmmain
    override fun getLayoutId() = R.layout.activity_main
    override fun getViewModel() = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this

        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""

        viewModel.fetchUsers()
        viewModel.fetchChats(supportFragmentManager) {
            view_pager.adapter = it
            tab_layout.setupWithViewPager(view_pager)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logout -> {
                viewModel.logout()
                startActivity(intentFor<StartActivity>().clearTop())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateStatus("online")
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateStatus("offline")
    }
}
