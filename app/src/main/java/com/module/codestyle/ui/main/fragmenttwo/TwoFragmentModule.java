package com.module.codestyle.ui.main.fragmenttwo;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.module.codestyle.conn.ApiService;
import com.module.codestyle.ui.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Module
public class TwoFragmentModule {
    @Provides
    public TwoViewModel twoViewModel(ApiService apiService) {
        return new TwoViewModel(apiService);
    }

    @Provides
    public ViewModelProvider.Factory provideTwoViewModel(TwoViewModel twoViewModel) {
        return new ViewModelProviderFactory(twoViewModel);
    }

    @Provides
    public LinearLayoutManager provideLinearLayoutManager(TwoFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    public AdapterTwoFragment provideAdapterTwoFragment() {
        return new AdapterTwoFragment();
    }
}
