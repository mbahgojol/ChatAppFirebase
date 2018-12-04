package com.bpdsulteng.mobile.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.databinding.library.baseAdapters.BR;
import com.bpdsulteng.mobile.R;
import com.bpdsulteng.mobile.databinding.ActivityMainBinding;
import com.bpdsulteng.mobile.ui.base.BaseActivity;
import com.bpdsulteng.mobile.ui.main.fragmentone.OneFragment;
import com.bpdsulteng.mobile.ui.main.fragmenttwo.TwoFragment;
import com.bpdsulteng.mobile.utils.ClassHelper;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, HasSupportFragmentInjector {
    private ActivityMainBinding binding;
    @Inject
    public MainViewModel viewModel;
    @Inject
    public DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel.setNavigator(this);
        setupView();
    }

    private void setupView() {
        ClassHelper.addFragment(this,
                R.id.container,
                new OneFragment(),
                "ONEFRAGMENT");
    }

    @Override
    public int getBindingVariable() {
        return BR.vmmain;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void gotoNextFragment() {
        ClassHelper.replaceFragment(this,
                R.id.container,
                new TwoFragment(),
                "TWOFRAGMENT",
                "ONEFRAGMENT");
    }

    @Override
    public void gotoPrevFragment() {
        ClassHelper.backFragment(this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
