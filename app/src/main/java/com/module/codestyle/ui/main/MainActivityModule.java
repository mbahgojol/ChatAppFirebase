package com.module.codestyle.ui.main;

import com.module.codestyle.conn.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Module
public class MainActivityModule {
    @Provides
    public MainViewModel provideMainViewModel(ApiService apiService) {
        return new MainViewModel(apiService);
    }
}
