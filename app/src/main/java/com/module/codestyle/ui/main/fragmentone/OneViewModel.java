package com.module.codestyle.ui.main.fragmentone;

import android.databinding.ObservableArrayList;

import com.module.codestyle.conn.ApiService;
import com.module.codestyle.ui.base.BaseViewModel;

import java.util.List;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class OneViewModel extends BaseViewModel<OneNavigator> {
    private ApiService apiService;
    private ObservableArrayList<String> listOne = new ObservableArrayList();

    @Override
    public void init() {

    }

    public OneViewModel(ApiService apiService) {
        this.apiService = apiService;
    }


    public void setListOne(List<String> data) {
        listOne.clear();
        listOne.addAll(data);
    }

    public ObservableArrayList<String> getListOne() {
        return listOne;
    }
}
