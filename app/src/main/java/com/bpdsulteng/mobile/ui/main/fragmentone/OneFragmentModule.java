package com.bpdsulteng.mobile.ui.main.fragmentone;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.bpdsulteng.mobile.conn.ApiManager;
import com.bpdsulteng.mobile.conn.ApiService;
import com.bpdsulteng.mobile.ui.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Module
public class OneFragmentModule {
    @Provides
    public OneViewModel oneViewModel(ApiManager apiManager) {
        return new OneViewModel(apiManager);
    }

    @Provides
    public ViewModelProvider.Factory provideOneViewModel(OneViewModel oneViewModel) {
        return new ViewModelProviderFactory(oneViewModel);
    }

    @Provides
    public LinearLayoutManager provideLinearLayoutManager(OneFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    public AdapterOneFragment provideAdapterOneFragment() {
        return new AdapterOneFragment();
    }
}