package com.module.codestyle.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.databinding.library.baseAdapters.BR;
import com.module.codestyle.R;
import com.module.codestyle.databinding.ActivityMainBinding;
import com.module.codestyle.ui.base.BaseActivity;
import com.module.codestyle.ui.main.fragmentone.OneFragment;
import com.module.codestyle.ui.main.fragmenttwo.TwoFragment;
import com.module.codestyle.utils.ClassHelper;

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
