package com.module.codestyle.ui.main.fragmentone;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.module.codestyle.R;
import com.module.codestyle.databinding.FragmentOneBinding;
import com.module.codestyle.ui.base.BaseAdapterRecyclerview;
import com.module.codestyle.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends BaseFragment<FragmentOneBinding, OneViewModel> implements OneNavigator, BaseAdapterRecyclerview.OnItemClickListener<String> {

    private FragmentOneBinding binding;
    private OneViewModel viewModel;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    @Inject
    public AdapterOneFragment adapterOneFragment;
    @Inject
    public LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        setupView();
    }

    private void setupView() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rcFragmentOne.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        binding.rcFragmentOne.setHasFixedSize(true);
        binding.rcFragmentOne.setAdapter(adapterOneFragment);
        adapterOneFragment.setOnItemClickListener(this);
        initData();
    }

    @Override
    public int getBindingVariable() {
        return BR.vmone;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    public OneViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(OneViewModel.class);
        return viewModel;
    }

    @Override
    public void onItemClick(View view, String item) {
        Toast.makeText(getBaseActivity(), item, Toast.LENGTH_SHORT).show();
    }

    public void initData() {
        String[] name = getResources().getStringArray(R.array.club_name);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            data.add(name[i]);
        }
        viewModel.setListOne(data);
    }
}
