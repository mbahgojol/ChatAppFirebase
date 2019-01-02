package com.bpdsulteng.mobile.ui.main;

import android.databinding.ObservableField;

import com.bpdsulteng.mobile.conn.ApiService;
import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class MainViewModel extends BaseObservableViewModel<MainNavigator> {
    public ObservableField<String> keterangan = new ObservableField<>();
    public ApiService apiService;

    public MainViewModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public void init() {

    }
}
